package com.example.batchexample.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DeciderJobConfig {
    //Step의 결과에 따라 분기 시키는 것은 Step의 역할이 2개 이상이 됨
    //이로 인해 분기 로직의 처리에 어려움이 따름

    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job deciderJob() {
        return jobBuilderFactory.get("deciderJob")
                .start(startStep())
                .next(decider())
                .from(decider())
                    .on("ODD")
                    .to(oddStep())
                .from(decider())
                    .on("ENEN")
                    .to(eventStep())
                .end()
                .build();
    }

    @Bean
    public Step startStep() {
        return stepBuilderFactory.get("startStep")
                .tasklet(((stepContribution, chunkContext) -> {
                    log.info("!!!!!!!! start");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new oddDecider();
    }

    @Bean
    public Step eventStep() {
        return stepBuilderFactory.get("eventStep")
                .tasklet(((stepContribution, chunkContext) -> {
                    log.info("!!!!!! 짝수");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    @Bean
    public Step oddStep() {
        return stepBuilderFactory.get("oddStep")
                .tasklet(((stepContribution, chunkContext) -> {
                    log.info("!!!!! 홀수");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }

    public static class oddDecider implements JobExecutionDecider {

        @Override
        public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
            Random random = new Random();

            int randomNumber = random.nextInt(50) + 1;
            log.info("!!!!!!! randomNumber : {}", randomNumber);

            if (randomNumber % 2 == 0) {
                return new FlowExecutionStatus("EVEN");
            }
            return new FlowExecutionStatus("ODD");
        }
    }
}
