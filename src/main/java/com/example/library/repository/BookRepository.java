package com.example.library.repository;

import com.example.library.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * Data-access contract for books. Kept as an interface so the service
 * layer depends on an abstraction, not on JdbcTemplate directly - this
 * is also what makes the service layer trivially mockable in unit tests.
 */
public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findAll();

    boolean existsByIsbn(String isbn);

    void update(Book book);

    void deleteById(Long id);

    /** Atomically decrement available copies; returns true if a copy was actually available. */
    boolean decrementAvailableCopies(Long bookId);

    void incrementAvailableCopies(Long bookId);
}