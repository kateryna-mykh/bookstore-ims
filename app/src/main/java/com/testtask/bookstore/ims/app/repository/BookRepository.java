package com.testtask.bookstore.ims.app.repository;

import com.testtask.bookstore.ims.app.model.Book;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, UUID> {

}
