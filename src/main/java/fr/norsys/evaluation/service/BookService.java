package fr.norsys.evaluation.service;

import fr.norsys.evaluation.entity.Book;
import fr.norsys.evaluation.entity.User;
import fr.norsys.evaluation.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository ;
    public Book saveBook(Book book) {
        return  bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void DeleteBook(Long id) {
        Optional<Book> deletedBook = bookRepository.findById(id);
        bookRepository.delete(deletedBook.get());
    }

    public List<Book> getBooksByTitle(String title){
        return  bookRepository.findBookByTitle(title);
    }
}
