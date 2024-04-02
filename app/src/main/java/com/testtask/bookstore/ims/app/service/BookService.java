package com.testtask.bookstore.ims.app.service;

import com.testtask.bookstore.ims.app.dto.CreateNewBookRequestDto;
import com.testtask.bookstore.ims.app.dto.BookFullTransferDto;
import com.testtask.bookstore.ims.app.dto.BookUpdateResponseDto;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Mono<BookFullTransferDto> save(CreateNewBookRequestDto bookDto);

    Mono<BookFullTransferDto> findById(UUID id);

    Flux<BookFullTransferDto> findAll();

    Mono<BookUpdateResponseDto> update(BookFullTransferDto bookDto);

    Mono<Void> deleteById(UUID id);
}
