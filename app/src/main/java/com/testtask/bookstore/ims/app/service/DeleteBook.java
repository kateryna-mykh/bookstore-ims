package com.testtask.bookstore.ims.app.service;

import java.util.UUID;
import reactor.core.publisher.Mono;

public interface DeleteBook {
    Mono<Void> deleteById(UUID id);
}
