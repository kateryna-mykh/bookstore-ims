package com.testtask.bookstore.ims.app.grpc;

import com.testtask.bookstore.ims.app.service.BookServiceImpl;
import com.testtask.bookstore.ims.proto.BookManagementServiceGrpc.BookManagementServiceImplBase;
import com.testtask.bookstore.ims.proto.BookResponse;
import com.testtask.bookstore.ims.proto.CreateBookRequest;
import io.grpc.stub.StreamObserver;

//@GrpcService
public class GrpcBookService extends BookManagementServiceImplBase {
private BookServiceImpl bookService;
    
    @Override
    public void createBook(CreateBookRequest request, StreamObserver<BookResponse> responseObserver) {
        BookResponse response = BookResponse.newBuilder()
                .setAuthor(request.getAuthor())
                .setTitle(request.getTitle())
                .setId(null).build();
        responseObserver.onNext(response);// method to send it to the client
        responseObserver.onCompleted();// call onCompleted() to specify that we’ve finished dealing with the RPC;
                                       // otherwise, the connection will be hung, and the client will just wait for
                                       // more information to come in.
    }
}
