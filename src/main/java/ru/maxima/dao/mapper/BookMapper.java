package ru.maxima.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getLong("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year_of_publication"));
//        book.setPersonId(rs.getLong("person_id"));
        return book;
    }
}
