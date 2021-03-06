package javaRush.repository;

import javaRush.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleStartsWithIgnoreCase(String lastName);
}
