package mock;

import model.Book;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookMock {

    public static boolean empty = false;

    public static List<Book> personResponseList(){
        if (empty) Stream.empty();
        return Stream.of(bookMock(1L), bookMock(2L)).collect(Collectors.toList());
    }

    public static Book bookMock(Long id){
        return Book.builder()
                .id(id)
                .title("TITTLE")
                .author("AUTHOR")
                .yearOfPublication(2000)
                .isbn("ISBN")
                .genre("GENRE")
                .statusBook("STATUS")
                .dateRegister(new Date().toString())
                .build();
    }
}
