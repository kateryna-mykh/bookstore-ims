package com.testtask.bookstore.ims.app.service;

import com.testtask.bookstore.ims.app.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl {
    private final BookRepository repository;

}
