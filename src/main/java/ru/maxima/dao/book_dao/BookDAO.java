package ru.maxima.dao.book_dao;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.maxima.models.Book;
import ru.maxima.models.Person;

import java.util.List;


public interface BookDAO {
    @Transactional
    List<Book> getAllBooks();

    @Transactional
    Book findBookById(Long bookId);

    @Transactional
    void save(Book book);

    @Transactional
    void update(Book book, Long bookId);

    @Transactional
    void delete(Long id);

    @Transactional
    void assignBookToPerson(Long bookId, Long personId);

    @Transactional
    void freeBook(Long bookId);
}
