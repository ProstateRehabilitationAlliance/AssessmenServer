package com.prostate.assessmen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix  //@EnableCircuitBreaker  ==  @EnableHystrix
@EnableFeignClients
@EnableEurekaServer
@SpringBootApplication
@MapperScan(basePackages = {"com.prostate.assessmen.mapper"})
//@SpringCloudApplication
public class AssessmenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmenApplication.class, args);
	}
}
