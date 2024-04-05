package fr.norsys.evaluation.repository;

import fr.norsys.evaluation.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

@Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE %:title%")
    List<Book> findBookByTitle(String title);

}
