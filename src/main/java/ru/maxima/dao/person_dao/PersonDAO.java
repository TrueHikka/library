package ru.maxima.dao.person_dao;

import ru.maxima.models.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> getAllPeople();
    Person findPersonById(Long id);
    void save(Person person);
    void update(Person person, Long id);
    void delete(Long id);
}
