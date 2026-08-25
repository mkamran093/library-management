package com.example.library.dto.borrow;

import jakarta.validation.constraints.NotNull;

/**
 * Inbound DTO to issue a book to a member.
 * The client supplies only the two IDs involved; dates and status
 * are computed by the service layer, not passed in.
 */
public class BorrowRequestDTO {

    @NotNull(message = "bookId is required")
    private Long bookId;

    @NotNull(message = "memberId is required")
    private Long memberId;

    public BorrowRequestDTO() {
    }

    public BorrowRequestDTO(Long bookId, Long memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
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
}