package service;

import model.Book;
import repositories.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    public Book save(){
        return null;
    }

    public List<Book> listAll(){
        return bookRepository.listAll();
    }

    public Integer quantityOfBooks(){
        return null;
    }

    public Book fetchBookById(Long id){
        return null;
    }

    public Book fetchBookByIsbn(){
        return null;
    }
}
