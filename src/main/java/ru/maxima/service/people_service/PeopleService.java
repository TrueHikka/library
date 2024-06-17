package ru.maxima.service.people_service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.models.Person;
import ru.maxima.repository.PeopleRepository;

import java.util.List;

@Service
public class PeopleService implements PeopleServiceinf{
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public List<Person> getAllPeople() {
        return  peopleRepository.findAll();
    }

    @Override
    public Person findPersonById(Long id) {
        Person person = peopleRepository.findById(id).orElse(null);
        if (person != null) {
            Hibernate.initialize(person.getBooks());
        }

        return person;
    }

    @Override
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Override
    public void update(Person person, Long id) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Override
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }
}
