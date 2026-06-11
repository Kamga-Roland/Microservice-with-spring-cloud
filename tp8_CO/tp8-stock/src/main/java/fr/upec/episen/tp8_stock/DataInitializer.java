package fr.upec.episen.tp8_stock;

import fr.upec.episen.tp8_stock.model.ProductStock;
import fr.upec.episen.tp8_stock.repository.StockRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
	protected Logger logger = LoggerFactory.getLogger(DataInitializer.class);


    @Bean
    CommandLineRunner initDatabase(StockRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                // On pré-remplit avec des stocks pour les faux produits "P1" et "P2"
                repository.save(new ProductStock("1", 50));
                repository.save(new ProductStock("2", 12));
                logger.info("Base MongoDB initialisée avec des stocks de test.");
            }
        };
    }
}