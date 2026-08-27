package com.example.library.dto.book;


public record BookPatchDTO(
        String title,
        String author,
        String isbn,
        Integer publicationYear,
        Integer totalCopies,
        Integer availableCopies
) {
}
