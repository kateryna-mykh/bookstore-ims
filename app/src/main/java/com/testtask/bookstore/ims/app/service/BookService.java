package com.testtask.bookstore.ims.app.service;

import com.testtask.bookstore.ims.app.dto.BookRequestDto;
import com.testtask.bookstore.ims.app.dto.BookResponseDto;
import com.testtask.bookstore.ims.app.dto.BookUpdateResponseDto;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Mono<BookResponseDto> save(BookRequestDto bookDto);

    Mono<BookResponseDto> findById(UUID id);

    Flux<BookResponseDto> findAll();

    Mono<BookUpdateResponseDto> update(BookResponseDto bookDto);

    Mono<Void> deleteById(UUID id);
}
