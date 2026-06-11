package fr.upec.episen.tp8_server;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service; // CORRECTION : Remplacer l'import de GrpcService

import fr.upec.episen.tp8_server.ProductServiceGrpc.ProductServiceImplBase;
import fr.upec.episen.tp8_server.ProductServiceOuterClass.GetProductRequest;
import fr.upec.episen.tp8_server.ProductServiceOuterClass.GetProductResponse;
import fr.upec.episen.tp8_server.ProductServiceOuterClass.ListProductsRequest;
import fr.upec.episen.tp8_server.ProductServiceOuterClass.ListProductsResponse;
import fr.upec.episen.tp8_stock.StockRequest;
import fr.upec.episen.tp8_stock.StockResponse;
import fr.upec.episen.tp8_stock.StockServiceGrpc;
import io.grpc.stub.StreamObserver;

@Service // Devient un composant Spring standard géré par GrpcServerConfig
public class ProductMicroService extends ProductServiceImplBase {
    
    protected Logger logger = LoggerFactory.getLogger(ProductMicroService.class);
    protected List<Product> products;
    private final StockServiceGrpc.StockServiceBlockingStub stockStub;

    // Le constructeur reste identique et propre
    public ProductMicroService(StockServiceGrpc.StockServiceBlockingStub stockStub) {
        super();
        this.stockStub = stockStub;
        this.products = List.of(
            new Product(1, "Product 1", 10.0),
            new Product(2, "Product 2", 20.0),
            new Product(3, "Product 3", 30.0)
        );
    }

    @Override
    public void getProduct(GetProductRequest request, StreamObserver<GetProductResponse> responseObserver) {
        int productId = request.getId();
        
        Product product = products.stream()
            .filter(p -> p.getId() == productId)
            .findFirst()
            .orElse(null);
            
        GetProductResponse response;
        
        if (product != null) {
            int stockQuantity = 0;
            try {
                StockRequest stockRequest = StockRequest.newBuilder()
                    .setProductId(String.valueOf(productId))
                    .build();
                
                logger.info("Appel gRPC via Eureka vers tp8-stock pour le produit " + productId);
                
                // Utilisation du Stub injecté par Spring
                StockResponse stockResponse = stockStub.getStockByProductId(stockRequest);
                stockQuantity = stockResponse.getQuantity();
                
                logger.info("Stock récupéré via Eureka pour le produit " + productId + " : " + stockQuantity);
            } catch (Exception e) {
                logger.error("Échec de la communication gRPC avec tp8-stock : " + e.getMessage());
            }

            response = GetProductResponse.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setQuantity(stockQuantity) 
                .build();
        } else {
            response = GetProductResponse.newBuilder().build();
        }
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listProducts(ListProductsRequest request, StreamObserver<ListProductsResponse> responseObserver) {
        ListProductsResponse.Builder responseBuilder = ListProductsResponse.newBuilder();
        
        request.getIdsList().forEach(id -> {
            Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
                
            if (product != null) {
                int stockQuantity = 0;
                try {
                    StockRequest stockRequest = StockRequest.newBuilder()
                        .setProductId(String.valueOf(id))
                        .build();
                    
                    // Utilisation du Stub injecté par Spring
                    StockResponse stockResponse = stockStub.getStockByProductId(stockRequest);
                    stockQuantity = stockResponse.getQuantity();
                } catch (Exception e) {
                    logger.error("Impossible de récupérer le stock pour le produit " + id + " : " + e.getMessage());
                }

                GetProductResponse response = GetProductResponse.newBuilder()
                    .setId(product.getId())
                    .setName(product.getName())
                    .setPrice(product.getPrice())
                    .setQuantity(stockQuantity)
                    .build();
                    
                responseBuilder.addProducts(response);
            }
        });
        
        responseObserver.onNext(responseBuilder.build());   
        responseObserver.onCompleted();
    }
}