package com.koreait.Surl_project_11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestSurlProject11Application {

    public static void main(String[] args) {
        SpringApplication.run(TestSurlProject11Application.class, args);
    }

}
