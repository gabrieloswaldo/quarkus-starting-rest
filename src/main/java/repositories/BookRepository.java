package repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import model.Book;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class BookRepository implements PanacheRepositoryBase<Book,Long> {
}
