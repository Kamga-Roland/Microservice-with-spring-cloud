package fr.upec.episen.tp8_stock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import fr.upec.episen.tp8_stock.model.ProductStock;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<ProductStock, String> {
  Optional<ProductStock> findByProductId(String productId);

  List<ProductStock> findAll();
}
