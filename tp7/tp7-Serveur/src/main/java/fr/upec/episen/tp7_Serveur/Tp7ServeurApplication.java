package fr.upec.episen.tp7_Serveur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
	
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // permet à ce service de s'enregistrer auprès d'Eureka
public class Tp7ServeurApplication implements CommandLineRunner {

	protected Logger logger = LoggerFactory.getLogger(Tp7ServeurApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Tp7ServeurApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("gRPC server is running...");
	}
}
