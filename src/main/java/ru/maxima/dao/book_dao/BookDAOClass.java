package ru.maxima.dao.book_dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.maxima.dao.mapper.BookMapper;
import ru.maxima.models.Book;

import java.util.Comparator;
import java.util.List;

@Repository
public class BookDAOClass implements BookDAO{
    private final JdbcTemplate jdbcTemplate;

    public BookDAOClass(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query("select * from book", new BookMapper());
    }

    @Override
    public Book findBookById(Long bookId) {
        return jdbcTemplate.queryForObject("select * from book where book_id =?", new Object[]{bookId}, new BookMapper());
    }

    @Override
    public void save(Book book) {
//        if(book.getBookId() == null) {
//            List<Book> books = getAllBooks();
//            if (!books.isEmpty()) {
//                book.setBookId(books.stream()
//                        .map(Book::getBookId)
//                        .max(Comparator.naturalOrder())
//                        .orElse(0L) + 1);
//            }
//        }

        jdbcTemplate.update("insert into book (title, author, year_of_publication, person_id) values (?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear(), book.getPersonId());
    }

    @Override
    public void update(Book book, Long bookId) {
        jdbcTemplate.update("update book set title=?, author=?, year_of_publication=?, person_id=? WHERE book_id=?", book.getTitle(), book.getAuthor(), book.getYear(), book.getPersonId(), bookId);
    }

    @Override
    public void delete(Long bookId) {
        jdbcTemplate.update("delete from book where book_id=?", bookId);
    }

    @Override
    public void assignBookToPerson(Long bookId, Long personId) {
        jdbcTemplate.update("update book set person_id=? where book_id=?", personId, bookId);
    }

    @Override
    public void freeBook(Long bookId) {
        jdbcTemplate.update("update book set person_id=null where book_id=?", bookId);
    }

}
