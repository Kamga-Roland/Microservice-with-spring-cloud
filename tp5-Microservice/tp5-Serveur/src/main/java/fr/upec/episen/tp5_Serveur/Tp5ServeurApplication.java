package fr.upec.episen.tp5_Serveur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp5ServeurApplication implements CommandLineRunner {

	protected Logger logger = LoggerFactory.getLogger(Tp5ServeurApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Tp5ServeurApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("gRPC server is running...");
	}
}
