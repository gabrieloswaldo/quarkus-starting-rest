package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String author;

    @Column(name = "year_of_publication")
    Integer yearOfPublication;

    String isbn;

    String genre;

    @Column(name = "status_book")
    String statusBook;

    @Column(name = "date_register")
    String dateRegister;
}
