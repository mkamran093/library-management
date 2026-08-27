package com.example.library.dto.book;

public record BookResponseDTO(
    Long id, String title, String author, String isbn, boolean available
) {
}