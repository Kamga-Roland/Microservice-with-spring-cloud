package fr.upec.episen.tp9_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // Permet de s'enregistrer auprès d'Eureka
public class Tp9ServerApplication implements CommandLineRunner {
	protected Logger logger = LoggerFactory.getLogger(Tp9ServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Tp9ServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Initialization logic for the server
		logger.info("Initializing the gRPC server...");
	}

}
