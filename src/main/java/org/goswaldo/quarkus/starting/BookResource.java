package org.goswaldo.quarkus.starting;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookRepository bookRepository;

    @Inject
    Logger logger;

    @GET
    public List<Book> getAllBooks() {
        logger.info("Returning all books");
        return bookRepository.getAllBooks();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countBooks() {
        logger.info("Returning the number of books");
        return bookRepository.getAllBooks().size();
    }

    @GET
    @Path("/{id}")
    public Optional<Book> getBook(@PathParam("id") final int bookId) {
        logger.info("Returning the book with id " + bookId);
        return bookRepository.getAllBooks().stream()
                .filter(book -> bookId == book.getId())
                .findFirst();
    }
}