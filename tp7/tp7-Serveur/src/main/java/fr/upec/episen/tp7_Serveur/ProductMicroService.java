package fr.upec.episen.tp7_Serveur;

import java.util.List;

import org.springframework.grpc.server.service.GrpcService;

import fr.upec.episen.tp7_server.ProductServiceGrpc.ProductServiceImplBase;
import fr.upec.episen.tp7_server.ProductServiceOuterClass.GetProductRequest;
import fr.upec.episen.tp7_server.ProductServiceOuterClass.GetProductResponse;
import fr.upec.episen.tp7_server.ProductServiceOuterClass.ListProductsRequest;
import fr.upec.episen.tp7_server.ProductServiceOuterClass.ListProductsResponse;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ProductMicroService extends ProductServiceImplBase {
    protected List<Product> products;

    public ProductMicroService() {
        super();
        // Initialize the list of products
        this.products = List.of(
                new Product(1, "Product 1", 10.0),
                new Product(2, "Product 2", 20.0),
                new Product(3, "Product 3", 30.0));
    }

    @Override
    public void getProduct(GetProductRequest request, StreamObserver<GetProductResponse> responseObserver) {
        int productId = request.getId();
        Product product = products.stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);
        GetProductResponse response = null;
        if (product != null) {
            response = GetProductResponse.newBuilder()
                    .setId(product.getId())
                    .setName(product.getName())
                    .setPrice(product.getPrice())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Product not found"));
        }
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
                GetProductResponse responseB = GetProductResponse.newBuilder()
                        .setId(product.getId())
                        .setName(product.getName())
                        .setPrice(product.getPrice())
                        .build();
                responseBuilder.addProducts(responseB);
            }
        });
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

}