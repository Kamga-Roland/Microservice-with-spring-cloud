package fr.upec.episen;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class Tp9ConfigServerApplication { 
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(Tp9ConfigServerApplication.class, args);
    }
}