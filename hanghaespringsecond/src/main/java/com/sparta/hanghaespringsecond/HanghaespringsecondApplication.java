package com.sparta.hanghaespringsecond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaespringsecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanghaespringsecondApplication.class, args);
	}

}
