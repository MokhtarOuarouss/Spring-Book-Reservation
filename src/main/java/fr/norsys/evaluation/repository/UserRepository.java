package fr.norsys.evaluation.repository;

import fr.norsys.evaluation.entity.Book;
import fr.norsys.evaluation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.userName) LIKE %:name%")
    List<User> findUserByName(String name);

}
