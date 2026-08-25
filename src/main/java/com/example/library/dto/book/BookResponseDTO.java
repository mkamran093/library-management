package com.example.library.dto.book;

/**
 * Outbound DTO representing a Book as returned by the API.
 *
 * Unlike the request DTO, this includes server-assigned/derived fields
 * (`id`, `availableCopies`) that a client needs to see but never sets directly.
 * Keeping this separate from the domain model means we control exactly what
 * is exposed over the wire, independent of how `Book` is persisted.
 */
public class BookResponseDTO {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer publicationYear;
    private Integer totalCopies;
    private Integer availableCopies;

    public BookResponseDTO() {
    }

    public BookResponseDTO(Long id, String title, String author, String isbn,
                           Integer publicationYear, Integer totalCopies, Integer availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}