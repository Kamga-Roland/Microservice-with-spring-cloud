package fr.upec.episen.tp9_stock.repository;

import fr.upec.episen.tp9_stock.model.ProductStock;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface StockRepository extends MongoRepository<ProductStock, String> {
    Optional<ProductStock> findByProductId(String productId);
}