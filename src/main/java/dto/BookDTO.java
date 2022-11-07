package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    Long id;

    String title;

    String author;

    Integer yearOfPublication;

    String isbn;

    String genre;

    String statusBook;

    String dateRegister;
}
