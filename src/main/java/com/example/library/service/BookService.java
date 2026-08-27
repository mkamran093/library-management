package com.example.library.service;

import com.example.library.dto.book.BookRequestDTO;
import com.example.library.dto.book.BookResponseDTO;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<BookResponseDTO> getAll() {
        return bookRepository.findAll();
    }

    public BookResponseDTO getById(Long id) {
        Book book = bookRepository.findById(id);
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getAvailableCopies() > 0
        );
    }

    public BookResponseDTO create(@Valid BookRequestDTO request) {
        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setIsbn(request.isbn());
        book.setPublicationYear(request.publicationYear());
        book.setTotalCopies(request.totalCopies());
        book.setAvailableCopies(request.availableCopies());
        Long id = bookRepository.create(book);
        return new BookResponseDTO(
                id,
                request.title(),
                request.author(),
                request.isbn(),
                request.availableCopies() > 0
        );
    }

//    public BookResponseDTO update(Long id, @Valid BookRequestDTO request) {
//        Book existingBook = bookRepository.findById(id);
//        if (existingBook == null) {
//            throw new RuntimeException("Book not found");
//        }
//
//
//    }

    public void delete(Long id) {
        bookRepository.delete(id);
    }
}
