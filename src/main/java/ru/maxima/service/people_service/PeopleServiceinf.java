package ru.maxima.service.people_service;

import jakarta.transaction.Transactional;
import ru.maxima.models.Person;

import java.util.List;

public interface PeopleServiceinf {
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
