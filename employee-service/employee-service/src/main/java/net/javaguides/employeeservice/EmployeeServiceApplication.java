package net.javaguides.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
  We want to use OpenFeign to send http request to client. hence we
  applied @EnableFeignClients. This is similar to @ComponentScan.
  It searches for interfaces annotated with @FeignClient and provide its
  implementation where it implements the declared method of interface
  in way that we can call to send the http request to server. In declaration
  of method in interface we should follow following rules

  	. Method parameter list, annotation can be matched with server side request
  	  handler method.

  	. Returned type must compatible with response body payload.

 */
@EnableFeignClients
@SpringBootApplication
public class EmployeeServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
