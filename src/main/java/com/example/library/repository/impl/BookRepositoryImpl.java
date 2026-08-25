package com.example.library.repository.impl;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.repository.mapper.BookRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * JDBC-backed implementation of {@link BookRepository}.
 * Uses plain {@link JdbcTemplate} - no ORM/Hibernate - as requested.
 */
@Repository
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper rowMapper = new BookRowMapper();

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book save(Book book) {
        String sql = "INSERT INTO book (title, author, isbn, publication_year, total_copies, available_copies) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            if (book.getPublicationYear() != null) {
                ps.setInt(4, book.getPublicationYear());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            ps.setInt(5, book.getTotalCopies());
            ps.setInt(6, book.getAvailableCopies());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        book.setId(key != null ? key.longValue() : null);
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        try {
            Book book = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM book ORDER BY id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        String sql = "SELECT COUNT(*) FROM book WHERE isbn = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        return count != null && count > 0;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE book SET title = ?, author = ?, isbn = ?, publication_year = ?, " +
                "total_copies = ?, available_copies = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublicationYear(),
                book.getTotalCopies(), book.getAvailableCopies(), book.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    @Override
    public boolean decrementAvailableCopies(Long bookId) {
        // Only decrements when a copy is actually available - avoids a race
        // where two concurrent borrows could push available_copies negative.
        String sql = "UPDATE book SET available_copies = available_copies - 1 " +
                "WHERE id = ? AND available_copies > 0";
        int rows = jdbcTemplate.update(sql, bookId);
        return rows > 0;
    }

    @Override
    public void incrementAvailableCopies(Long bookId) {
        String sql = "UPDATE book SET available_copies = available_copies + 1 WHERE id = ?";
        jdbcTemplate.update(sql, bookId);
    }
}