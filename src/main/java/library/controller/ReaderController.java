package library.controller;


import library.Exception.BookNotAvailableException;
import library.Exception.NotARegisteredBook;
import library.Exception.ReaderNotFoundException;
import library.domain.Book;
import library.domain.BookStock;
import library.domain.Reader;
import library.service.BookService;
import library.service.ReaderService;
import library.service.StockService;
import library.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReaderController {

    private TransactionService transactionService;
    private StockService stockService;
    private ReaderService readerService;
    private BookService bookService;

    @Autowired
    public ReaderController(ReaderService readerService,BookService bookService,TransactionService transactionService,StockService stockService){
        this.readerService = readerService;
        this.bookService = bookService;
        this.transactionService = transactionService;
        this.stockService = stockService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello Readers";
    }

    @GetMapping("/name/{name}")
    public List<Reader> getUserByName(@PathVariable String name){
        return readerService.getByName(name);
    }

    @GetMapping("/id/{id}")
    public Optional<Reader> getUserById(@PathVariable String id) throws ReaderNotFoundException {
        try {
            return readerService.getByID(id);
        }catch (ReaderNotFoundException e){
            return Optional.empty();
        }
    }

    @PostMapping("/addReader")
    public String addName(@RequestBody(required = false) ReqBody reqBody){
        readerService.addReader(reqBody.firstName,reqBody.lastName);
        return "Reader is successfully added" + reqBody.firstName + reqBody;
    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestBody String userId) {
        readerService.removeReader(userId);
        return "Reader is deleted";
    }

    @PostMapping("/update")
    public String updateReader(@RequestBody UpdateRequest updateRequest) throws ReaderNotFoundException {
        try {
            readerService.updateReader(updateRequest.userID,updateRequest.lastName);
            return "Successfully updated the lastName";
        } catch (ReaderNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/allReader")
    public List<Reader> getAllReader(){
        return readerService.getAllReader();
    }

    @CrossOrigin
    @GetMapping("/allBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @CrossOrigin
    @PostMapping("/borrowBook")
    public ResponseEntity borrowBook(@RequestBody BorrowRequest borrowRequest ) throws ReaderNotFoundException {
        System.out.println(borrowRequest.isbn+"enrmf,erskjbdm,fre;sdlntmf,s" + borrowRequest.userId);
        try{
            transactionService.borrowBook(borrowRequest.userId,borrowRequest.isbn);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ReaderNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (BookNotAvailableException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/allStock")
    public List<BookStock> getAllBookStock(){
        return stockService.getAllBooks();
    }

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody AddBook addBook) throws NotARegisteredBook {
        try {
            Book book = new Book(addBook.title, addBook.author, addBook.isbn);
            bookService.addBook(book);
            stockService.addBookCopies(addBook.isbn,addBook.noOfCopies);
            bookService.getAllBooks();
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping("/validateReader")
    public ResponseEntity isValidReader(@RequestBody ValidateReader validateReader){
        if(readerService.isValidReader(validateReader.readerId)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping("/readerInfo/{readerId}")
    public ResponseEntity getReaderInfo(@PathVariable String readerId) throws ReaderNotFoundException {
        try{
            Reader reader = readerService.getReader(readerId);
            return new ResponseEntity(reader,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

//    @CrossOrigin
//    @PostMapping("/addBookCopies")
//    public ResponseEntity addBookCopies(@PathVariable AddBook addBook) throws NotARegisteredBook {
//        bookService.addBook(new Book(addBook.title,addBook.author,addBook.isbn));
//        stockService.addBookCopies(addBook.isbn,addBook.noOfCopies);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}

class AddBook{
    public String title;
    public String isbn;
    public String author;
    public String noOfCopies;
}

class ReqBody{
    public String firstName;
    public String lastName;
}

class ValidateReader{
    public String readerId;
}


class UpdateRequest{
    public String userID;
    public  String  lastName;
}

class BorrowRequest{
    public String userId;
    public String isbn;
}


class BookRequest{
    public String isbn;
    public String title;
    public String author;
}
