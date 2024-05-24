package ru.maxima.dao.person_dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.maxima.dao.mapper.BookMapper;
import ru.maxima.dao.mapper.PersonMapper;
import ru.maxima.models.Book;
import ru.maxima.models.Person;

import java.util.Comparator;
import java.util.List;

@Repository
public class PersonDAOCLass implements PersonDAO{

    private final JdbcTemplate jdbcTemplate;

    public PersonDAOCLass(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> getAllPeople() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    @Override
    public Person findPersonById(Long id) {
        Person person = jdbcTemplate.queryForObject("select * from person where id =?", new Object[]{id}, new PersonMapper());
        List<Book> books = jdbcTemplate.query("select * from book where person_id=?", new Object[]{id}, new BookMapper());
        person.setBooks(books);
        return person;
    }

    @Override
    public void save(Person person) {
        if (person.getId() == null) {
            List<Person> allPeople = getAllPeople();
            if (!allPeople.isEmpty()) {
                person.setId(allPeople.stream()
                        .map(Person::getId)
                        .max(Comparator.naturalOrder())
                        .orElse(0L) + 1);
            }
        }

        jdbcTemplate.update("insert into person(id, full_name, birth_year) values(?,?,?)", person.getId(),person.getName(), person.getBirthYear());

    }

    @Override
    public void update(Person person, Long id) {
        jdbcTemplate.update("update person set full_name=?, birth_year=? where id=?", person.getName(), person.getBirthYear(), id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }
}
