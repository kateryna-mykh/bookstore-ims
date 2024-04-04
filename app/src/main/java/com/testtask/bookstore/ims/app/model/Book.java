package com.testtask.bookstore.ims.app.model;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "books")
public class Book {
    @Id
    @Column("id")
    private UUID id;
    @Column("title")
    private String title;
    @Column("author")
    private String author;
    @Column("isbn")
    private String isbn;
    @Column("qty")
    private Integer quantity;
    @Column("description")
    private String description;
}
