package com.testtask.bookstore.ims.app.service;

import com.testtask.bookstore.ims.app.BookRequestDto;
import com.testtask.bookstore.ims.app.BookResponseDto;
import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Mono<BookResponseDto> save(BookRequestDto bookDto);
    Mono<BookResponseDto> findById(UUID id);
    Flux<List<BookResponseDto>> findByTitle(final String title);
    Flux<List<BookResponseDto>> findByAuthor(final String author);
    Flux<List<BookResponseDto>> findAll();
    Mono<BookResponseDto> update(UUID id, BookResponseDto bookDto);
    Mono<Void> deleteById(UUID id);
}
