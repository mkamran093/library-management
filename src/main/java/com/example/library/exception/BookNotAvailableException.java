package com.example.library.exception;

/** Thrown when a book has zero available copies and cannot be borrowed. */
public class BookNotAvailableException extends RuntimeException {

    public BookNotAvailableException(String message) {
        super(message);
    }
}