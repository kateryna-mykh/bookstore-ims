package com.testtask.bookstore.ims.app.service;

import com.testtask.bookstore.ims.app.dto.BookFullTransferDto;
import com.testtask.bookstore.ims.app.dto.BookUpdateResponseDto;
import com.testtask.bookstore.ims.app.dto.CreateNewBookRequestDto;
import reactor.core.publisher.Mono;

public interface CreateUpdateBook {
    Mono<BookFullTransferDto> save(CreateNewBookRequestDto bookDto);

    Mono<BookUpdateResponseDto> update(BookFullTransferDto bookDto);
}
