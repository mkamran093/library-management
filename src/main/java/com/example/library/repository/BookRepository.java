package com.example.library.repository;

import com.example.library.dto.book.BookResponseDTO;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Book;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private final String url =
            "jdbc:mysql://localhost:3306/library_db?useSSL=false&serverTimezone=UTC";
    private final String username = "root";
    private final String password = "root";

    public List<BookResponseDTO> findAll() {

        List<BookResponseDTO> books = new ArrayList<>();
        String sql = "SELECT id, title, author, isbn, available_copies FROM books";
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                )
        {
            while(rs.next()) {
                BookResponseDTO book = new BookResponseDTO(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("available_copies") > 0
                );

                books.add(book);
            }

        } catch(SQLException e) {
            throw new RuntimeException("Error finding books");
        }

        return books;
    }

    public Book findById(Long id) {

        String sql = "SELECT id, title, author, isbn, publication_year FROM books WHERE id = ?";
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getLong("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setPublicationYear(rs.getInt("publication_year"));
                    return book;
                }
            }

        } catch (Exception e) {
            throw new ResourceNotFoundException("Book with id " + id + " Not Found");
        }
        return null;
    }

    public Long create(Book book) {

        String sql = "INSERT INTO books (title, author, isbn, publication_year, total_copies, available_copies) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setInt(4, book.getPublicationYear());
            ps.setInt(5, book.getTotalCopies());
            ps.setInt(6, book.getAvailableCopies());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return  rs.getLong(1);
                }
                throw new RuntimeException("Failed to retrieve generated book id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Long id) {

        String sql = "DELETE FROM books WHERE id = ?";
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = connection.prepareStatement(sql);
                ) {
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

