package com.testtask.bookstore.ims.app.service;

import com.testtask.bookstore.ims.app.dto.BookFullTransferDto;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindBook {
    Mono<BookFullTransferDto> findById(UUID id);

    Flux<BookFullTransferDto> findAll();
}
