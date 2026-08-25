package com.example.library.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Inbound DTO for creating/updating a Book.
 *
 * Deliberately excludes `id` (assigned by the DB) and `availableCopies`
 * (derived/managed by the server, not something a client should set directly).
 * This is the contract of what a caller is ALLOWED to send us.
 */
public class BookRequestDTO {

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "author is required")
    private String author;

    @NotBlank(message = "isbn is required")
    private String isbn;

    private Integer publicationYear;

    @NotNull(message = "totalCopies is required")
    @Min(value = 1, message = "totalCopies must be at least 1")
    private Integer totalCopies;

    public BookRequestDTO() {
    }

    public BookRequestDTO(String title, String author, String isbn, Integer publicationYear, Integer totalCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }
}