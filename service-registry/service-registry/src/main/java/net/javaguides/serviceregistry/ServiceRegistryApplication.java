package net.javaguides.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
@EnableEurekaServer annotation make our web app as Spring Cloud
Discovery and Service Registry Server. Apart from this we also need
to provide the configuration in application.properties files
 */
@EnableEurekaServer
@SpringBootApplication
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
