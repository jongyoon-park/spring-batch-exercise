package com.example.batchexample.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity(name = "user_info")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Table(name = "user_info")
@EntityScan
public class UserInfo {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
