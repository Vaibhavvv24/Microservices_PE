package com.quizplatform.common;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: quiz.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QuizServiceGrpc {

  private QuizServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.quizplatform.common.QuizService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.quizplatform.common.QuizRequest,
      com.quizplatform.common.QuizResponse> getGetQuizMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetQuiz",
      requestType = com.quizplatform.common.QuizRequest.class,
      responseType = com.quizplatform.common.QuizResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.quizplatform.common.QuizRequest,
      com.quizplatform.common.QuizResponse> getGetQuizMethod() {
    io.grpc.MethodDescriptor<com.quizplatform.common.QuizRequest, com.quizplatform.common.QuizResponse> getGetQuizMethod;
    if ((getGetQuizMethod = QuizServiceGrpc.getGetQuizMethod) == null) {
      synchronized (QuizServiceGrpc.class) {
        if ((getGetQuizMethod = QuizServiceGrpc.getGetQuizMethod) == null) {
          QuizServiceGrpc.getGetQuizMethod = getGetQuizMethod =
              io.grpc.MethodDescriptor.<com.quizplatform.common.QuizRequest, com.quizplatform.common.QuizResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetQuiz"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizplatform.common.QuizRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizplatform.common.QuizResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QuizServiceMethodDescriptorSupplier("GetQuiz"))
              .build();
        }
      }
    }
    return getGetQuizMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.quizplatform.common.ResultRequest,
      com.quizplatform.common.ResultResponse> getSubmitResultMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitResult",
      requestType = com.quizplatform.common.ResultRequest.class,
      responseType = com.quizplatform.common.ResultResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.quizplatform.common.ResultRequest,
      com.quizplatform.common.ResultResponse> getSubmitResultMethod() {
    io.grpc.MethodDescriptor<com.quizplatform.common.ResultRequest, com.quizplatform.common.ResultResponse> getSubmitResultMethod;
    if ((getSubmitResultMethod = QuizServiceGrpc.getSubmitResultMethod) == null) {
      synchronized (QuizServiceGrpc.class) {
        if ((getSubmitResultMethod = QuizServiceGrpc.getSubmitResultMethod) == null) {
          QuizServiceGrpc.getSubmitResultMethod = getSubmitResultMethod =
              io.grpc.MethodDescriptor.<com.quizplatform.common.ResultRequest, com.quizplatform.common.ResultResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitResult"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizplatform.common.ResultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizplatform.common.ResultResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QuizServiceMethodDescriptorSupplier("SubmitResult"))
              .build();
        }
      }
    }
    return getSubmitResultMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.quizplatform.common.GetAllResultsRequest,
      com.quizplatform.common.GetAllResultsResponse> getGetAllResultsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllResults",
      requestType = com.quizplatform.common.GetAllResultsRequest.class,
      responseType = com.quizplatform.common.GetAllResultsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.quizplatform.common.GetAllResultsRequest,
      com.quizplatform.common.GetAllResultsResponse> getGetAllResultsMethod() {
    io.grpc.MethodDescriptor<com.quizplatform.common.GetAllResultsRequest, com.quizplatform.common.GetAllResultsResponse> getGetAllResultsMethod;
    if ((getGetAllResultsMethod = QuizServiceGrpc.getGetAllResultsMethod) == null) {
      synchronized (QuizServiceGrpc.class) {
        if ((getGetAllResultsMethod = QuizServiceGrpc.getGetAllResultsMethod) == null) {
          QuizServiceGrpc.getGetAllResultsMethod = getGetAllResultsMethod =
              io.grpc.MethodDescriptor.<com.quizplatform.common.GetAllResultsRequest, com.quizplatform.common.GetAllResultsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllResults"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizplatform.common.GetAllResultsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.quizplatform.common.GetAllResultsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QuizServiceMethodDescriptorSupplier("GetAllResults"))
              .build();
        }
      }
    }
    return getGetAllResultsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QuizServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QuizServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QuizServiceStub>() {
        @java.lang.Override
        public QuizServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QuizServiceStub(channel, callOptions);
        }
      };
    return QuizServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QuizServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QuizServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QuizServiceBlockingStub>() {
        @java.lang.Override
        public QuizServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QuizServiceBlockingStub(channel, callOptions);
        }
      };
    return QuizServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QuizServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QuizServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QuizServiceFutureStub>() {
        @java.lang.Override
        public QuizServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QuizServiceFutureStub(channel, callOptions);
        }
      };
    return QuizServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getQuiz(com.quizplatform.common.QuizRequest request,
        io.grpc.stub.StreamObserver<com.quizplatform.common.QuizResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetQuizMethod(), responseObserver);
    }

    /**
     */
    default void submitResult(com.quizplatform.common.ResultRequest request,
        io.grpc.stub.StreamObserver<com.quizplatform.common.ResultResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubmitResultMethod(), responseObserver);
    }

    /**
     */
    default void getAllResults(com.quizplatform.common.GetAllResultsRequest request,
        io.grpc.stub.StreamObserver<com.quizplatform.common.GetAllResultsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllResultsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service QuizService.
   */
  public static abstract class QuizServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QuizServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service QuizService.
   */
  public static final class QuizServiceStub
      extends io.grpc.stub.AbstractAsyncStub<QuizServiceStub> {
    private QuizServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuizServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QuizServiceStub(channel, callOptions);
    }

    /**
     */
    public void getQuiz(com.quizplatform.common.QuizRequest request,
        io.grpc.stub.StreamObserver<com.quizplatform.common.QuizResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetQuizMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void submitResult(com.quizplatform.common.ResultRequest request,
        io.grpc.stub.StreamObserver<com.quizplatform.common.ResultResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubmitResultMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllResults(com.quizplatform.common.GetAllResultsRequest request,
        io.grpc.stub.StreamObserver<com.quizplatform.common.GetAllResultsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllResultsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service QuizService.
   */
  public static final class QuizServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QuizServiceBlockingStub> {
    private QuizServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuizServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QuizServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.quizplatform.common.QuizResponse getQuiz(com.quizplatform.common.QuizRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetQuizMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.quizplatform.common.ResultResponse submitResult(com.quizplatform.common.ResultRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubmitResultMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.quizplatform.common.GetAllResultsResponse getAllResults(com.quizplatform.common.GetAllResultsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllResultsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service QuizService.
   */
  public static final class QuizServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<QuizServiceFutureStub> {
    private QuizServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuizServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QuizServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.quizplatform.common.QuizResponse> getQuiz(
        com.quizplatform.common.QuizRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetQuizMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.quizplatform.common.ResultResponse> submitResult(
        com.quizplatform.common.ResultRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubmitResultMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.quizplatform.common.GetAllResultsResponse> getAllResults(
        com.quizplatform.common.GetAllResultsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllResultsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_QUIZ = 0;
  private static final int METHODID_SUBMIT_RESULT = 1;
  private static final int METHODID_GET_ALL_RESULTS = 2;

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
        case METHODID_GET_QUIZ:
          serviceImpl.getQuiz((com.quizplatform.common.QuizRequest) request,
              (io.grpc.stub.StreamObserver<com.quizplatform.common.QuizResponse>) responseObserver);
          break;
        case METHODID_SUBMIT_RESULT:
          serviceImpl.submitResult((com.quizplatform.common.ResultRequest) request,
              (io.grpc.stub.StreamObserver<com.quizplatform.common.ResultResponse>) responseObserver);
          break;
        case METHODID_GET_ALL_RESULTS:
          serviceImpl.getAllResults((com.quizplatform.common.GetAllResultsRequest) request,
              (io.grpc.stub.StreamObserver<com.quizplatform.common.GetAllResultsResponse>) responseObserver);
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
          getGetQuizMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.quizplatform.common.QuizRequest,
              com.quizplatform.common.QuizResponse>(
                service, METHODID_GET_QUIZ)))
        .addMethod(
          getSubmitResultMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.quizplatform.common.ResultRequest,
              com.quizplatform.common.ResultResponse>(
                service, METHODID_SUBMIT_RESULT)))
        .addMethod(
          getGetAllResultsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.quizplatform.common.GetAllResultsRequest,
              com.quizplatform.common.GetAllResultsResponse>(
                service, METHODID_GET_ALL_RESULTS)))
        .build();
  }

  private static abstract class QuizServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QuizServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.quizplatform.common.QuizServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QuizService");
    }
  }

  private static final class QuizServiceFileDescriptorSupplier
      extends QuizServiceBaseDescriptorSupplier {
    QuizServiceFileDescriptorSupplier() {}
  }

  private static final class QuizServiceMethodDescriptorSupplier
      extends QuizServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    QuizServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (QuizServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QuizServiceFileDescriptorSupplier())
              .addMethod(getGetQuizMethod())
              .addMethod(getSubmitResultMethod())
              .addMethod(getGetAllResultsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
