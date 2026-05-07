package fr.upec.episen.tp4.server;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * methode retourner les évaluation des produits
 * </pre>
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class EvaluateServiceGrpc {

  private EvaluateServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "fr.upec.episen.tp4.server.EvaluateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fr.upec.episen.tp4.server.Evaluation.EvaluationRequest,
      fr.upec.episen.tp4.server.Evaluation.EvaluationResponse> getEvaluateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Evaluate",
      requestType = fr.upec.episen.tp4.server.Evaluation.EvaluationRequest.class,
      responseType = fr.upec.episen.tp4.server.Evaluation.EvaluationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fr.upec.episen.tp4.server.Evaluation.EvaluationRequest,
      fr.upec.episen.tp4.server.Evaluation.EvaluationResponse> getEvaluateMethod() {
    io.grpc.MethodDescriptor<fr.upec.episen.tp4.server.Evaluation.EvaluationRequest, fr.upec.episen.tp4.server.Evaluation.EvaluationResponse> getEvaluateMethod;
    if ((getEvaluateMethod = EvaluateServiceGrpc.getEvaluateMethod) == null) {
      synchronized (EvaluateServiceGrpc.class) {
        if ((getEvaluateMethod = EvaluateServiceGrpc.getEvaluateMethod) == null) {
          EvaluateServiceGrpc.getEvaluateMethod = getEvaluateMethod =
              io.grpc.MethodDescriptor.<fr.upec.episen.tp4.server.Evaluation.EvaluationRequest, fr.upec.episen.tp4.server.Evaluation.EvaluationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Evaluate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fr.upec.episen.tp4.server.Evaluation.EvaluationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fr.upec.episen.tp4.server.Evaluation.EvaluationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EvaluateServiceMethodDescriptorSupplier("Evaluate"))
              .build();
        }
      }
    }
    return getEvaluateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EvaluateServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EvaluateServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EvaluateServiceStub>() {
        @java.lang.Override
        public EvaluateServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EvaluateServiceStub(channel, callOptions);
        }
      };
    return EvaluateServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static EvaluateServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EvaluateServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EvaluateServiceBlockingV2Stub>() {
        @java.lang.Override
        public EvaluateServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EvaluateServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return EvaluateServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EvaluateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EvaluateServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EvaluateServiceBlockingStub>() {
        @java.lang.Override
        public EvaluateServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EvaluateServiceBlockingStub(channel, callOptions);
        }
      };
    return EvaluateServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EvaluateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EvaluateServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EvaluateServiceFutureStub>() {
        @java.lang.Override
        public EvaluateServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EvaluateServiceFutureStub(channel, callOptions);
        }
      };
    return EvaluateServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * methode retourner les évaluation des produits
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void evaluate(fr.upec.episen.tp4.server.Evaluation.EvaluationRequest request,
        io.grpc.stub.StreamObserver<fr.upec.episen.tp4.server.Evaluation.EvaluationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEvaluateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EvaluateService.
   * <pre>
   * methode retourner les évaluation des produits
   * </pre>
   */
  public static abstract class EvaluateServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EvaluateServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EvaluateService.
   * <pre>
   * methode retourner les évaluation des produits
   * </pre>
   */
  public static final class EvaluateServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EvaluateServiceStub> {
    private EvaluateServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EvaluateServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EvaluateServiceStub(channel, callOptions);
    }

    /**
     */
    public void evaluate(fr.upec.episen.tp4.server.Evaluation.EvaluationRequest request,
        io.grpc.stub.StreamObserver<fr.upec.episen.tp4.server.Evaluation.EvaluationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEvaluateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EvaluateService.
   * <pre>
   * methode retourner les évaluation des produits
   * </pre>
   */
  public static final class EvaluateServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<EvaluateServiceBlockingV2Stub> {
    private EvaluateServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EvaluateServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EvaluateServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public fr.upec.episen.tp4.server.Evaluation.EvaluationResponse evaluate(fr.upec.episen.tp4.server.Evaluation.EvaluationRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getEvaluateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service EvaluateService.
   * <pre>
   * methode retourner les évaluation des produits
   * </pre>
   */
  public static final class EvaluateServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EvaluateServiceBlockingStub> {
    private EvaluateServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EvaluateServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EvaluateServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public fr.upec.episen.tp4.server.Evaluation.EvaluationResponse evaluate(fr.upec.episen.tp4.server.Evaluation.EvaluationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEvaluateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EvaluateService.
   * <pre>
   * methode retourner les évaluation des produits
   * </pre>
   */
  public static final class EvaluateServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EvaluateServiceFutureStub> {
    private EvaluateServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EvaluateServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EvaluateServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<fr.upec.episen.tp4.server.Evaluation.EvaluationResponse> evaluate(
        fr.upec.episen.tp4.server.Evaluation.EvaluationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEvaluateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EVALUATE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EVALUATE:
          serviceImpl.evaluate((fr.upec.episen.tp4.server.Evaluation.EvaluationRequest) request,
              (io.grpc.stub.StreamObserver<fr.upec.episen.tp4.server.Evaluation.EvaluationResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getEvaluateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              fr.upec.episen.tp4.server.Evaluation.EvaluationRequest,
              fr.upec.episen.tp4.server.Evaluation.EvaluationResponse>(
                service, METHODID_EVALUATE)))
        .build();
  }

  private static abstract class EvaluateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EvaluateServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return fr.upec.episen.tp4.server.Evaluation.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EvaluateService");
    }
  }

  private static final class EvaluateServiceFileDescriptorSupplier
      extends EvaluateServiceBaseDescriptorSupplier {
    EvaluateServiceFileDescriptorSupplier() {}
  }

  private static final class EvaluateServiceMethodDescriptorSupplier
      extends EvaluateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    EvaluateServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EvaluateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EvaluateServiceFileDescriptorSupplier())
              .addMethod(getEvaluateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
