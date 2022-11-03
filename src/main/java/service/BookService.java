package service;

import dto.BookDTO;
import enums.BookStatus;
import exceptions.BusinessException;
import model.Book;
import model.Verify;
import repositories.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    public Book save(Book book){
        String isbn = UUID.randomUUID().toString();
        book.setIsbn(isbn);
        book.setStatusBook(BookStatus.DISPONIVEL.toString());
        book.setDateRegister(new Date().toString());
        bookRepository.persistAndFlush(book);
        return book;
    }

    public List<Book> listAll(){
        return bookRepository
                .findAll()
                .stream()
                .filter(b->b.getStatusBook().equals(BookStatus.DISPONIVEL.toString()))
                .collect(Collectors.toList());
    }

    public Integer quantityOfBooks(){
        return bookRepository
                .findAll()
                .stream()
                .filter(b->b.getStatusBook().equals("DISPONIVEL"))
                .collect(Collectors.toList())
                .size();
    }

    public Optional<Book> fetchBookById(Long id){
        Optional<Book> fetchId = bookRepository.findByIdOptional(id).stream().findFirst();
        return Optional.ofNullable(fetchId.orElseThrow(()-> new BusinessException("ID NOT FOUND OR NOT EXISTS")));
    }

    public Optional<Book> fetchBookByIsbn(String isbn){
       Optional<Book> fetch = bookRepository.find("isbn",isbn).stream().findFirst();
        return Optional.ofNullable(fetch.orElseThrow(()-> new BusinessException("ISBN NOT FOUND")));
    }

    private Book findID(Long id) {
        Optional<Book> p = bookRepository.find("id", id).firstResultOptional();
        return p.orElseThrow(()-> new BusinessException("ID NOT FOUND OR NOT EXISTS"));
    }

    public Book updateBook(Long id, BookDTO dto){
        Book book = findID(id);
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setYearOfPublication(dto.getYearOfPublication());
        book.setGenre(dto.getGenre());
        book.setStatusBook(dto.getStatusBook());
        book.setDateRegister(new Date().toString());

        return book;
    }

    public Optional<Book> changeStatus(Long id, String status){

        Optional<Book> fetchStatus = bookRepository.find("id", id).stream().findFirst();

        if (fetchStatus.isEmpty() || fetchStatus.get().getId() == null){
            throw new BusinessException("BOOK NOT UPDATED");
        }

        if (status == BookStatus.DISPONIVEL.toString()){
            fetchStatus.get().setId(id);
            fetchStatus.get().setStatusBook(status);
            bookRepository.persistAndFlush(fetchStatus.get());
        }else {
            fetchStatus.get().setId(id);
            fetchStatus.get().setStatusBook(status);
            bookRepository.persistAndFlush(fetchStatus.get());
        }

        return fetchStatus;
    }

    public List<Book> findBookStatus(String status){

        List<Book> listBookStatus = bookRepository
                .find("statusBook", status)
                .stream()
                .filter(b->b.getStatusBook().contains(status))
                .collect(Collectors.toList());

        if (listBookStatus.isEmpty()){
            throw new BusinessException("BOOK STATUS NOT FOUND [LIST IS EMPTY]");
        }

        return listBookStatus;
    }

    public Verify verify() throws UnknownHostException {
        return Verify.builder()
                .date(new Date().toString())
                .address(InetAddress.getLocalHost().toString())
                .build();
    }
}
