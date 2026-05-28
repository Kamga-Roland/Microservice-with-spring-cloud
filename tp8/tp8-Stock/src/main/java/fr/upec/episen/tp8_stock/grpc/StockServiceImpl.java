package fr.upec.episen.tp8_stock.grpc;

import fr.upec.episen.tp8_stock.repository.StockRepository;
import fr.upec.episen.tp8_stock.model.ProductStock;
import fr.upec.episen.tp8_stock.GetStockRequest;
import fr.upec.episen.tp8_stock.GetStockResponse;
import fr.upec.episen.tp8_stock.StockServiceGrpc.StockServiceImplBase;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import java.util.Optional;

@org.springframework.grpc.server.service.GrpcService
public class StockServiceImpl extends StockServiceImplBase {

  private final StockRepository repo;

  public StockServiceImpl(StockRepository repo) {
    this.repo = repo;
  }

  @Override
  public void getStock(GetStockRequest request, StreamObserver<GetStockResponse> responseObserver) {
    Optional<ProductStock> opt = repo.findByProductId(request.getProductId());
    if (opt.isPresent()) {
      ProductStock p = opt.get();
      GetStockResponse resp = GetStockResponse.newBuilder()
          .setId(p.getId() == null ? "" : p.getId())
          .setProductId(p.getProductId() == null ? "" : p.getProductId())
          .setQuantity(p.getQuantity())
          .build();
      responseObserver.onNext(resp);
      responseObserver.onCompleted();
    } else {
      responseObserver.onError(io.grpc.Status.NOT_FOUND
          .withDescription("Product not found in stock")
          .asRuntimeException());
    }
  }

  @Override
  public void listStocks(Empty request, StreamObserver<GetStockResponse> responseObserver) {
    repo.findAll().forEach(p -> {
      GetStockResponse stockMsg = GetStockResponse.newBuilder()
          .setId(p.getId() == null ? "" : p.getId())
          .setProductId(p.getProductId() == null ? "" : p.getProductId())
          .setQuantity(p.getQuantity())
          .build();
      responseObserver.onNext(stockMsg);
    });
    responseObserver.onCompleted();
  }
}
