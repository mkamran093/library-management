package com.example.library.repository;

import com.example.library.dto.book.BookPatchDTO;
import com.example.library.dto.book.BookRequestDTO;
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

    public BookResponseDTO update(Long id, BookRequestDTO request) {
        String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, publication_year = ?, total_copies = ?, available_copies = ? where id = ?";
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = connection.prepareStatement(sql);
                ){
            ps.setString(1, request.title());
            ps.setString(2, request.author());
            ps.setString(3, request.isbn());
            ps.setInt(4, request.publicationYear());
            ps.setInt(5, request.totalCopies());
            ps.setInt(6, request.availableCopies());
            ps.setLong(7, id);

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new BookResponseDTO(id, request.title(), request.author(), request.isbn(), request.availableCopies() > 0);
    }

    public int update(Long id, BookPatchDTO request) {
        StringBuilder sql = new StringBuilder("UPDATE books SET ");
        List<Object> params = new ArrayList<>();

        if (request.title() != null) {
            sql.append("title = ?, ");
            params.add(request.title());
        }

        if (request.author() != null) {
            sql.append("author = ?, ");
            params.add(request.author());
        }

        if (request.isbn() != null) {
            sql.append("isbn = ?, ");
            params.add(request.isbn());
        }

        if (request.publicationYear() != null) {
            sql.append("publication_year = ?, ");
            params.add(request.publicationYear());
        }

        if (request.totalCopies() != null) {
            sql.append("total_copies = ?, ");
            params.add(request.totalCopies());
        }

        if (request.availableCopies() != null) {
            sql.append("available_copies = ?, ");
            params.add(request.availableCopies());
        }

        // Remove the last comma
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(id);

        try (Connection connection = DriverManager.getConnection(
                url, username, password);
             PreparedStatement statement =
                     connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

