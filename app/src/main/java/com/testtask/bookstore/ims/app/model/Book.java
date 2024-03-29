package com.testtask.bookstore.ims.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "books")
public class Book {
    @Id
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "qty")
    private Integer quantity;
    @Column(name = "description")
    private String description;
}
