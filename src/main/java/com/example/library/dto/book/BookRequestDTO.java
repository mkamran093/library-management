package com.example.library.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequestDTO(
        @NotNull @NotBlank(message = "Title is required") String title,
        @NotNull @NotBlank(message = "Author is required") String author,
        @NotNull @NotBlank(message = "ISBN is required") String isbn,
        int publicationYear,
        int totalCopies,
        int availableCopies
) {
}