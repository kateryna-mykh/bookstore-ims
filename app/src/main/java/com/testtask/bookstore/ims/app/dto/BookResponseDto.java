package com.testtask.bookstore.ims.app.dto;

import java.util.UUID;

public record BookResponseDto(UUID id, String title, String author, String isbn, Integer quantity, String description) {

}
