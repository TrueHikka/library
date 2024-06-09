package ru.maxima.dao.person_dao;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.springframework.stereotype.Repository;
import ru.maxima.models.Person;


import java.util.List;

@Repository
public class PersonDAOCLass implements PersonDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAOCLass(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Person> getAllPeople() {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("from Person", Person.class);
        return query.getResultList();
    }

    @Override
    public Person findPersonById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        Hibernate.initialize(person.getBooks());
        return person;
    }

    @Override
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Override
    public void update(Person person, Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.update(person);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);
    }
}
