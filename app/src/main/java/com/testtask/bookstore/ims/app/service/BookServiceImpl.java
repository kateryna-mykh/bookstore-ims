package com.testtask.bookstore.ims.app.service;

import com.testtask.bookstore.ims.app.dto.BookFullTransferDto;
import com.testtask.bookstore.ims.app.dto.BookUpdateResponseDto;
import com.testtask.bookstore.ims.app.dto.CreateNewBookRequestDto;
import com.testtask.bookstore.ims.app.mapper.BookDtoEntityMapper;
import com.testtask.bookstore.ims.app.repository.BookRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements CreateUpdateBook, FindBook, DeleteBook {
    private final BookRepository repository;
    private final BookDtoEntityMapper bookMapper;

    @Override
    public Mono<BookFullTransferDto> save(CreateNewBookRequestDto bookDto) {
        return repository.save(bookMapper.dtoToModel(bookDto)).map(bookMapper::modelToDto);
    }

    @Override
    public Mono<BookFullTransferDto> findById(UUID id) {
        return repository.findById(id).map(bookMapper::modelToDto);
    }

    @Override
    public Flux<BookFullTransferDto> findAll() {
        return repository.findAll().map(bookMapper::modelToDto);
    }

    @Override
    public Mono<BookUpdateResponseDto> update(BookFullTransferDto bookDto) {
        return repository.existsById(bookDto.id()).map(Boolean::booleanValue)
                .doOnNext(v -> {
                    if (v.booleanValue()) {
                        repository.save(bookMapper.dtoWithIdToModel(bookDto));
                        }
                }).map(value -> bookMapper.toUpdatedDto(bookDto.id(), value));
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return repository.deleteById(id);
    }
}
