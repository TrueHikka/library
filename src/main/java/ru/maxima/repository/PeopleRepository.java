package ru.maxima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {

}
