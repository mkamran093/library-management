package com.example.library.repository.mapper;

import com.example.library.model.BorrowRecord;
import com.example.library.model.BorrowStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Maps a single row of the `borrow_record` table to a {@link BorrowRecord} domain object. */
public class BorrowRecordRowMapper implements RowMapper<BorrowRecord> {

    @Override
    public BorrowRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        BorrowRecord record = new BorrowRecord();
        record.setId(rs.getLong("id"));
        record.setBookId(rs.getLong("book_id"));
        record.setMemberId(rs.getLong("member_id"));
        record.setBorrowDate(rs.getDate("borrow_date").toLocalDate());
        record.setDueDate(rs.getDate("due_date").toLocalDate());
        Date returnDate = rs.getDate("return_date");
        record.setReturnDate(returnDate != null ? returnDate.toLocalDate() : null);
        record.setStatus(BorrowStatus.valueOf(rs.getString("status")));
        return record;
    }
}