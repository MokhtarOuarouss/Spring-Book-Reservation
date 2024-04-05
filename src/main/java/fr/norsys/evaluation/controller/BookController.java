package fr.norsys.evaluation.controller;

import fr.norsys.evaluation.entity.Book;
import fr.norsys.evaluation.entity.User;
import fr.norsys.evaluation.service.BookService;
import fr.norsys.evaluation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin("*")
public class BookController {
    @Autowired
    private BookService bookService ;
    @Autowired
    private UserService userService;

    @PostMapping
    public void CreateBook(@RequestBody Book book) {
        // Assuming book.getOwner() returns a User entity
        User owner = book.getOwner();

        // Check if the owner is a new entity (not yet saved in the database)
        if (owner.getId() == null) {
            // Save the owner entity first
            userService.saveUser(owner);
        }

        book.setOwner(owner);

        bookService.saveBook(book);

        System.out.println(book.getOwner().getId());

        ResponseEntity.ok("Book Created successfully " + book.getOwner());
    }


    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long  id){
        Optional<Book> book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable long id, @RequestBody Book updatedBook) throws Exception {
        Optional<Book> existingBookOptional = bookService.getBookById(id);

        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();

            if (updatedBook.getTitle() != null) {
                existingBook.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getOwner() != null) {
                existingBook.setOwner(updatedBook.getOwner());
            }
            if (updatedBook.getPrice() != 0) {
                existingBook.setPrice(updatedBook.getPrice());
            }

            // Save the updated book
            this.CreateBook(existingBook);
            return ResponseEntity.ok(null);

        } else {
            throw new Exception("Book id not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        bookService.DeleteBook(id);
        return ResponseEntity.ok(null);

    }

    @GetMapping("/search/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title ) {
        return bookService.getBooksByTitle(title);

    }
}
