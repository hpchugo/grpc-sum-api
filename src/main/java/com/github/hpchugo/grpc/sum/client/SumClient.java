package com.github.hpchugo.grpc.sum.client;

import com.proto.sum.Sum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SumClient {

    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext() //Disable ssl
                .build();

        System.out.println("Creating stub");

        SumServiceGrpc.SumServiceBlockingStub sumClientSync = SumServiceGrpc.newBlockingStub(channel);

        Sum sum = Sum.newBuilder()
                .setNumberOne(10)
                .setNumberTwo(3)
                .build();

        SumRequest sumRequest = SumRequest.newBuilder()
                .setSum(sum)
                .build();

        SumResponse sumResponse = sumClientSync.sum(sumRequest);

        System.out.println(sumResponse.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
