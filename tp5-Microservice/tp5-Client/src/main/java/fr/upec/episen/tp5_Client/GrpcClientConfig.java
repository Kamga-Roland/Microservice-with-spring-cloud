package fr.upec.episen.tp5_Client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

import fr.upec.episen.tp5_server.ProductServiceGrpc;
import fr.upec.episen.tp5_server.ProductServiceGrpc.ProductServiceBlockingStub;


@Configuration
public class GrpcClientConfig {

    @Bean
    public ProductServiceBlockingStub productServiceClient(GrpcChannelFactory channelFactory) {
        return ProductServiceGrpc.newBlockingStub(channelFactory.createChannel("productClient"));
    }

}
