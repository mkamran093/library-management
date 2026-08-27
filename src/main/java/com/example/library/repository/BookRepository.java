package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    ArrayList<Book> books = new ArrayList<>();

    public List<Book> findAll() {
        return books;
    }

    public Book findById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void create(Book book) {
        books.add(book);
    }

    public void delete(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
