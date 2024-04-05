package fr.norsys.evaluation.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String email;


    @OneToMany(mappedBy = "owner")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Book> UserOwnedBooks;
    /*
    @OneToMany(mappedBy = "user")
    private List<Book> readBooks;

    @OneToMany(mappedBy = "user")
    private List<Evaluation> reviews;

    @OneToMany(mappedBy = "user")
    private List<Book> wishlists;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getUserOwnedBooks() {
        return this.UserOwnedBooks;
    }

    public void setUserOwnedBooks(List<Book> userOwnedBooks) {
        UserOwnedBooks = userOwnedBooks;
    }
/*

    public List<Book> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(List<Book> readBooks) {
        this.readBooks = readBooks;
    }

    public List<Book> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<Book> wishlists) {
        this.wishlists = wishlists;
    }*/
}
