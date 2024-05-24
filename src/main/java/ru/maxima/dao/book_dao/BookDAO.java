package ru.maxima.dao.book_dao;

import ru.maxima.models.Book;
import ru.maxima.models.Person;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks();
    Book findBookById(Long bookId);
    void save(Book book);
    void update(Book book, Long bookId);
    void delete(Long id);

    void assignBookToPerson(Long bookId, Long personId);

    void freeBook(Long bookId);
}
