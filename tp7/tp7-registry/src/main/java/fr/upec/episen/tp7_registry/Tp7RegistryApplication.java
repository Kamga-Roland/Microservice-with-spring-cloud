package fr.upec.episen.tp7_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Tp7RegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp7RegistryApplication.class, args);
	}

}
