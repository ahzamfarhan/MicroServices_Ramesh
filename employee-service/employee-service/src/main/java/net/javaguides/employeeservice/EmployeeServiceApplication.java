package net.javaguides.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class EmployeeServiceApplication {

	/*
		Injecting WebClient to send HTTP synchronous/asynchronous
		request to other microservices
	 */
	@Bean
	public WebClient webClient() {

		/*
			Creating WebClient using builder pattern. No use of Constructor
		 */
		return WebClient.builder().build();
	}
	public static void main(String[] args) {

		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
