package com.example.library.controller;

import com.example.library.dto.book.BookPatchDTO;
import com.example.library.dto.book.BookRequestDTO;
import com.example.library.dto.book.BookResponseDTO;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public List<BookResponseDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookResponseDTO getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public BookResponseDTO create(@Valid @RequestBody BookRequestDTO request) {
        return bookService.create(request);
    }

    @PutMapping("/{id}")
    public BookResponseDTO update(@PathVariable Long id,
                                  @Valid @RequestBody BookRequestDTO request) {
        return bookService.update(id, request);
    }

    @PatchMapping("/{id}")
    public void patch(@PathVariable Long id, @RequestBody BookPatchDTO request) {
        bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }


}
