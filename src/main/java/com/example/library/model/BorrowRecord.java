package com.example.library.model;

import java.time.LocalDate;

/**
 * Domain model mapped 1:1 to the `borrow_record` table.
 * Represents a single "book borrowed by member" transaction.
 */
public class BorrowRecord {

    private Long id;
    private Long bookId;
    private Long memberId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private BorrowStatus status;

    public BorrowRecord() {
    }

    public BorrowRecord(Long id, Long bookId, Long memberId, LocalDate borrowDate,
                        LocalDate dueDate, LocalDate returnDate, BorrowStatus status) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BorrowStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowStatus status) {
        this.status = status;
    }
}