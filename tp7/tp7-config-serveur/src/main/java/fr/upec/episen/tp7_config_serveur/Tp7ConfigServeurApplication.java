package fr.upec.episen.tp7_config_serveur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableConfigServer // active le serveur de configuration
@EnableDiscoveryClient // permet à ce service de s'enregistrer auprès d'Eureka
public class Tp7ConfigServeurApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp7ConfigServeurApplication.class, args);
	}

}
