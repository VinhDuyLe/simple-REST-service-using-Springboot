package vinh.usfca.bookservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

//Define all the REST method in this class
@RestController
public class BookResource {

    //get BookService. Auto connect BookService instance to bookservice class
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/books/{bookId}")
    public Book retriveBook(@PathVariable int bookId) {
        Book book = bookService.searchBook(bookId);

        if(book == null) {
            throw new BookNotFoundException("Book not exists for ID" + bookId);
        }
        return book;
    }

    @PostMapping("/books") //add data
    public ResponseEntity<Object> createBook(@RequestBody Book book) {
        Book saveBook = bookService.addBook(book);

        URI newBookLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{bookId}")
                .buildAndExpand(saveBook.getBookID())
                .toUri();

        return ResponseEntity.created(newBookLocation).build();
    }
}
