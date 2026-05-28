package fr.upec.episen.tp8_stock.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "product_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStock {
  @Id
  private String id;
  private String productId;
  private int quantity;
}
