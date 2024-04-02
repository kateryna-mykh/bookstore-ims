package com.testtask.bookstore.ims.app.grpc;

import com.google.protobuf.Empty;
import com.testtask.bookstore.ims.app.mapper.BookDtoProtoMapper;
import com.testtask.bookstore.ims.app.service.BookServiceImpl;
import com.testtask.bookstore.ims.proto.BookFullObject;
import com.testtask.bookstore.ims.proto.BookUpdateResponse;
import com.testtask.bookstore.ims.proto.CreateBookRequest;
import com.testtask.bookstore.ims.proto.FindByIdRequest;
import com.testtask.bookstore.ims.proto.ReactorBookManagementServiceGrpc;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@GrpcService
public class GrpcBookService
        extends ReactorBookManagementServiceGrpc.BookManagementServiceImplBase {
    private final BookServiceImpl bookService;
    private final BookDtoProtoMapper bookProtoMapper;

    @Override
    public Mono<BookFullObject> createBook(Mono<CreateBookRequest> newBook) {
        return newBook.map(book -> bookProtoMapper.protoCreateToDto(book))
                .flatMap(bookDto -> bookService.save(bookDto))
                .map(createdbook -> bookProtoMapper.dtoToProto(createdbook));
    }

    @Override
    public Mono<BookUpdateResponse> update(Mono<BookFullObject> book) {
        return book.map(b -> bookProtoMapper.protoUpdateToDto(b))
                .flatMap(bookDto -> bookService.update(bookDto))
                .map(updatedInfo -> bookProtoMapper.dtoToUpdateProto(updatedInfo));
    }

    @Override
    public Mono<Empty> delete(Mono<FindByIdRequest> bookId) {
        bookId.map(id -> UUID.fromString(id.getBookId()))
                .flatMap(uuid -> bookService.deleteById(uuid));
        return Mono.empty();
    }

    @Override
    public Mono<BookFullObject> getById(Mono<FindByIdRequest> bookId) {
        return bookId.map(id -> UUID.fromString(id.getBookId()))
                .flatMap(uuid -> bookService.findById(uuid))
                .map(foundBook -> bookProtoMapper.dtoToProto(foundBook));
    }

    @Override
    public Flux<BookFullObject> getAllBooks(Mono<Empty> mockedObject) {
        return bookService.findAll().map(foundBooks -> bookProtoMapper.dtoToProto(foundBooks));
    }
}
