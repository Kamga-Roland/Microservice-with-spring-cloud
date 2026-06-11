package fr.upec.episen.tp8_stock.service;

import fr.upec.episen.tp8_stock.StockRequest;
import fr.upec.episen.tp8_stock.StockResponse;
import fr.upec.episen.tp8_stock.StockServiceGrpc;
import fr.upec.episen.tp8_stock.model.ProductStock;
import fr.upec.episen.tp8_stock.repository.StockRepository;
import io.grpc.stub.StreamObserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;
import java.util.Optional;

@GrpcService // Annotation Spring gRPC pour l'enregistrer automatiquement dans Tomcat
public class StockServiceImpl extends StockServiceGrpc.StockServiceImplBase {
	protected Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void getStockByProductId(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        String productId = request.getProductId();
        logger.info("Appel au service de stock pour le produit " + productId);
        // Recherche dans MongoDB
        Optional<ProductStock> productStock = stockRepository.findByProductId(productId);
        
        int quantity = productStock.map(ProductStock::getQuantity).orElse(0);

        StockResponse response = StockResponse.newBuilder()
                .setProductId(productId)
                .setQuantity(quantity)
                .build();
        logger.info("Stock demandé pour le produit " + productId + " : " + quantity);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}