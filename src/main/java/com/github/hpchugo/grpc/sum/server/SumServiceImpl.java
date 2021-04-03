package com.github.hpchugo.grpc.sum.server;

import com.proto.sum.Sum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.stub.StreamObserver;

public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase{
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        Sum sum = request.getSum();
        int numberOne = sum.getNumberOne();
        int numberTwo = sum.getNumberTwo();

        int result = numberOne+numberTwo;

        SumResponse response =SumResponse
                .newBuilder()
                .setResult(result)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
