package ru.maxima.dao.book_dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.springframework.stereotype.Repository;
import ru.maxima.models.Book;
import ru.maxima.models.Person;


import java.util.List;

@Repository
public class BookDAOClass implements BookDAO{
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAOClass(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Book> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        Query<Book> query = session.createQuery("from Book", Book.class);
        return query.getResultList();
    }

    @Override
    public Book findBookById(Long bookId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, bookId);
    }

    @Override
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    @Override
    public void update(Book book, Long bookId) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
    }

    @Override
    public void delete(Long bookId) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, bookId);
        session.delete(book);
    }

    @Override
    public void assignBookToPerson(Long bookId, Long personId) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, bookId);
        Person person = session.get(Person.class, personId);
        book.setBookOwner(person);
        session.update(book);
    }

    @Override
    public void freeBook(Long bookId) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, bookId);
        book.setBookOwner(null);
        session.update(book);
    }

}
