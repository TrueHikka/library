package ru.maxima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
