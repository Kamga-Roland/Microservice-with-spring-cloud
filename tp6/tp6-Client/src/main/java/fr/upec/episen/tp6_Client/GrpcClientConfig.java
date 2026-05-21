package fr.upec.episen.tp6_Client;

import fr.upec.episen.tp6_server.ProductServiceGrpc;
import fr.upec.episen.tp6_server.ProductServiceGrpc.ProductServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@Configuration
public class GrpcClientConfig {

    @Bean
    public ProductServiceBlockingStub productServiceClient(DiscoveryClient discoveryClient) {

        List<ServiceInstance> instances = discoveryClient.getInstances("tp6-Serveur");

        if (instances == null || instances.isEmpty()) {
            throw new IllegalStateException("No instances found in eureka for service: tp6-Serveur");
        }

        ServiceInstance instance = instances.get(0); // You can implement load balancing here if needed
        String host = instance.getHost();
        int port = instance.getPort();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // Disable TLS for simplicity, adjust as needed
                .build();

        return ProductServiceGrpc.newBlockingStub(channel);
    }

}
