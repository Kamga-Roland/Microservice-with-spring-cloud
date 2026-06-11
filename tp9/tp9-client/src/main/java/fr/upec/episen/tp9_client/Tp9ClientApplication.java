package fr.upec.episen.tp9_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import fr.upec.episen.tp9_server.ProductServiceOuterClass.GetProductRequest;
import fr.upec.episen.tp9_server.ProductServiceOuterClass.GetProductResponse;
import fr.upec.episen.tp9_server.ProductServiceGrpc.ProductServiceBlockingStub;

@SpringBootApplication
@EnableDiscoveryClient // Permet au client de communiquer avec l'annuaire
public class Tp9ClientApplication implements CommandLineRunner {
	protected Logger logger = LoggerFactory.getLogger(Tp9ClientApplication.class);

	// On laisse Spring Boot injecter le Bean configuré
	@Autowired
	private ProductServiceBlockingStub productServiceStub;

	public static void main(String[] args) {
		SpringApplication.run(Tp9ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Starting the gRPC client...");
		
		try {
			// Appel gRPC via le Stub injecté
			GetProductResponse response = productServiceStub.getProduct(
				GetProductRequest.newBuilder().setId(1).build()
			);
			
			logger.info("Received product: " + response);
			logger.info("product = " + response.getName() + " - " + response.getPrice());
		} catch (Exception e) {
			logger.error("Erreur lors de l'appel gRPC : ", e);
		}
	}
}