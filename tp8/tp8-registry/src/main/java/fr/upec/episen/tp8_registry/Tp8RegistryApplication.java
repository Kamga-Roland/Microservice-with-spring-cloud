package fr.upec.episen.tp8_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Tp8RegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp8RegistryApplication.class, args);
	}

}
