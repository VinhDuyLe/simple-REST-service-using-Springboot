package vinh.usfca.bookservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component //define the componet file
public class BookService {
    private static List<Book> books = new ArrayList<>();
    private int bookCount = 3;

    static {
        books.add(new Book(1, "Harry Porter", new Date()));
        books.add(new Book(2, "Lord of the Rings", new Date()));
        books.add(new Book(3, "Song of ice and fire", new Date()));
    }

    public List<Book> findAllBooks() {
        return books;
    }

    public Book addBook(Book book) {
        if (book.getBookID() == null)
            book.setBookID(bookCount++);
        books.add(book);
        return book;
    }

    public Book searchBook(Integer id) {
        for (Book book : books) {
            if (book.getBookID() == id) {
                return book;
            }
        }
        return null;
    }
}
