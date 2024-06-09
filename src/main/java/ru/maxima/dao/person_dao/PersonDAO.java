package ru.maxima.dao.person_dao;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ru.maxima.models.Person;

import java.util.List;


public interface PersonDAO {
    @Transactional
    List<Person> getAllPeople();

    @Transactional
    Person findPersonById(Long id);

    @Transactional
    void save(Person person);

    @Transactional
    void update(Person person, Long id);

    @Transactional
    void delete(Long id);
}
