package fr.upec.episen.server;

import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;

import fr.upec.episen.tp4.server.EvaluateServiceGrpc;
import fr.upec.episen.tp4.server.Evaluation.EvaluationRequest;
import fr.upec.episen.tp4.server.Evaluation.EvaluationResponse;

public class EvaluateServiceImpl extends EvaluateServiceGrpc.EvaluateServiceImplBase {

    protected Map<String, String> properties = new HashMap<>();

    public EvaluateServiceImpl() {
        properties.put("studentId", "12345");
        properties.put("courseId", "CS101");
        properties.put("grade", "A");
        properties.put("studentName", "John Doe");
    }

    @Override
    public void evaluate(EvaluationRequest request, StreamObserver<EvaluationResponse> responseObserver) {

        System.out.println("Received evaluation request: " + request);

        EvaluationResponse.Builder responseBuilder = EvaluationResponse.newBuilder();

        for (String propertyName : request.getPropertyNameList()) {
            // Example value, replace with real lookup logic
            responseBuilder.putProperties(propertyName,
                    properties.getOrDefault(propertyName, "value-for-" + propertyName));
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}