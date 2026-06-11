package fr.upec.episen.tp8_server;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.upec.episen.tp8_stock.StockServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

@Configuration
public class GrpcServerConfig {

    @Value("${grpc.server.port}")
    private int grpcServerPort;

    /**
     * Crée et démarre manuellement le serveur gRPC de tp8-server (Port 9091)
     */
    @Bean
    public Server grpcServer(ProductMicroService productMicroService) throws IOException {
        Server server = ServerBuilder.forPort(grpcServerPort)
                .addService(productMicroService)
                .build();
        
        server.start(); 
        System.out.println("[gRPC Server] Serveur de produits démarré sur le port : " + grpcServerPort);
        return server;
    }

    /**
     * Crée le client (Stub) dynamique pour appeler tp8-stock
     */
    @Bean
    public StockServiceGrpc.StockServiceBlockingStub stockServiceBlockingStub(DiscoveryClient discoveryClient) {
        List<ServiceInstance> instances = discoveryClient.getInstances("tp8-stock");

        if (instances == null || instances.isEmpty()) {
            throw new IllegalStateException("Aucune instance du service 'tp8-stock' n'a été trouvée dans Eureka !");
        }

        // Récupérer l'instance d'Eureka
        ServiceInstance instance = instances.get(0);
        String host = instance.getHost();
        
        // CORRECTION : On utilise le port d'enregistrement officiel d'Eureka (8383) 
        // car le service gRPC de stock s'exécute à l'intérieur du conteneur Web Tomcat de stock.
        int webPort = instance.getPort(); 

        System.out.println("[gRPC Client] Connexion vers tp8-stock établie sur " + host + ":" + webPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, webPort)
                .usePlaintext() 
                .build();

        return StockServiceGrpc.newBlockingStub(channel);
    }
}