package com.example.study_gather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StudyGatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyGatherApplication.class, args);
	}

}
