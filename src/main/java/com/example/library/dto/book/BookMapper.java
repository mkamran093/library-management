package com.example.library.dto.book;

import com.example.library.model.Book;

/**
 * Converts between the Book domain model and its DTOs.
 * Centralizing mapping here keeps controllers/services free of ad-hoc
 * field-copying and makes the model <-> wire-format boundary explicit.
 */
public final class BookMapper {

    private BookMapper() {
    }

    /** Build a new (unsaved) domain model from client input. id/availableCopies are server-assigned. */
    public static Book toNewModel(BookRequestDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getTotalCopies());
        return book;
    }

    public static BookResponseDTO toResponse(Book book) {
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
    }
}