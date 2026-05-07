package fr.upec.episen.tp5_Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.grpc.client.GrpcClientFactory;

import fr.upec.episen.tp5_Client.ProductServiceGrpc.ProductServiceBlockingStub;
import fr.upec.episen.tp5_Client.ProductServiceOuterClass.GetProductRequest;
import fr.upec.episen.tp5_Client.ProductServiceOuterClass.GetProductResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
public class Tp5ClientApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(Tp5ClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Tp5ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Starting gRPC client...");
		// Call methods of ProductService to test the gRPC communication with the server
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
		ProductServiceBlockingStub productServiceClient = ProductServiceGrpc.newBlockingStub(channel);

		// Example: Get a product
		GetProductResponse response = productServiceClient.getProduct(GetProductRequest.newBuilder().setId(1).build());
		logger.info("Received product: {}", response);

	}

}
