package fr.upec.episen.tp8_Serveur;

import java.util.List;

import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import fr.upec.episen.tp8_server.GetProductRequest;
import fr.upec.episen.tp8_server.GetProductResponse;
import fr.upec.episen.tp8_server.ListProductsRequest;
import fr.upec.episen.tp8_server.ListProductsResponse;
import fr.upec.episen.tp8_server.Product;
import fr.upec.episen.tp8_server.ProductServiceGrpc.ProductServiceImplBase;

@GrpcService
public class ProductMicroService extends ProductServiceImplBase {

    // Utilise ProductModel pour tes données internes (évite conflit avec Product protobuf)
    protected final List<ProductModel> products;

    public ProductMicroService() {
        super();
        this.products = List.of(
                new ProductModel(1, "Product 1", 10.0),
                new ProductModel(2, "Product 2", 20.0),
                new ProductModel(3, "Product 3", 30.0));
    }

    @Override
    public void getProduct(GetProductRequest request, StreamObserver<GetProductResponse> responseObserver) {
        int productId = request.getId();
        ProductModel found = products.stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);

        if (found != null) {
            // Construire la réponse en utilisant les builders protobuf
            GetProductResponse response = GetProductResponse.newBuilder()
                    .setProduct(
                            Product.newBuilder()
                                    .setId(found.getId())
                                    .setName(found.getName())
                                    .setPrice(found.getPrice())
                                    .build())
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
            ProductModel found = products.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
            if (found != null) {
                GetProductResponse responseB = GetProductResponse.newBuilder()
                        .setProduct(
                                Product.newBuilder()
                                        .setId(found.getId())
                                        .setName(found.getName())
                                        .setPrice(found.getPrice())
                                        .build())
                        .build();
                responseBuilder.addProducts(responseB);
            }
        });
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    // modèle interne pour éviter conflit de noms
    private static class ProductModel {
        private final int id;
        private final String name;
        private final double price;
        ProductModel(int id, String name, double price) { this.id = id; this.name = name; this.price = price; }
        int getId() { return id; }
        String getName() { return name; }
        double getPrice() { return price; }
    }
}
