package fr.upec.episen.tp8_client;

import fr.upec.episen.tp8_server.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GrpcClientConfig {

    @Bean
    public ProductServiceGrpc.ProductServiceBlockingStub productServiceStub(DiscoveryClient discoveryClient) {
        // 1. Récupérer l'instance du serveur depuis Eureka
        List<ServiceInstance> instances = discoveryClient.getInstances("tp8-server");
        
        if (instances == null || instances.isEmpty()) {
            throw new IllegalStateException("Aucune instance du service 'tp8-server' n'a été trouvée dans Eureka !");
        }

        ServiceInstance instance = instances.get(0);
        String host = instance.getHost();

        // Récupérer le port gRPC depuis les métadonnées transmises par Eureka
        int port = 9091; // Valeur par défaut
        if (instance.getMetadata().containsKey("grpc.port")) {
            port = Integer.parseInt(instance.getMetadata().get("grpc.port"));
        }

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        return ProductServiceGrpc.newBlockingStub(channel);
    }
}