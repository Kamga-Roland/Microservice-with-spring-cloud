package fr.upec.episen.tp7_Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.upec.episen.tp7_server.ProductServiceGrpc.ProductServiceBlockingStub;
import fr.upec.episen.tp7_server.ProductServiceOuterClass.GetProductRequest;
import fr.upec.episen.tp7_server.ProductServiceOuterClass.GetProductResponse;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // Enable Eureka Discovery Client
public class Tp7ClientApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(Tp7ClientApplication.class);

	@Autowired
	private ProductServiceBlockingStub productServiceStub;

	public static void main(String[] args) {
		SpringApplication.run(Tp7ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Starting gRPC client...");
		// Call methods of ProductService to test the gRPC communication with the server
		try {
			GetProductResponse response = productServiceStub.getProduct(
				GetProductRequest.newBuilder().setId(1).build()
			);
			logger.info("Received product: {}", response);
		} catch (Exception e) {
			logger.error("Error calling gRPC service: {}", e.getMessage());
		}

	}

}
