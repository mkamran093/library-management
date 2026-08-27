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

    Long nextId = 1L;

    @Autowired
    BookRepository bookRepository;
//    BookRepository bookRepository;
//
//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    public List<BookResponseDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        List<BookResponseDTO> response = new ArrayList<>();
        for (Book book: books) {
            response.add(new BookResponseDTO(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.isAvailable()
            ));
        }
        return response;
    }

    public BookResponseDTO getById(Long id) {
        Book book = bookRepository.findById(id);
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.isAvailable()
        );
    }

    public BookResponseDTO create(@Valid BookRequestDTO request) {
        Book book = new Book(
                nextId++,
                request.title(),
                request.author(),
                true
        );
        bookRepository.create(book);
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                request.author(),
                book.isAvailable()
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
