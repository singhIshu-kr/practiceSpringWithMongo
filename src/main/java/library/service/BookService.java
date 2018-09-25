package library.service;

import library.Exception.NoSuchBookFoundException;
import library.Exception.NotARegisteredBook;
import library.domain.Book;
import library.domain.BookStock;
import library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> getByName(String name) {
        return bookRepository.findByTitle(name);
    }

    public void removeBook(String isbnId) throws NoSuchBookFoundException {
        if(isBookTypePresent(isbnId)){
            bookRepository.deleteById(isbnId);
        }else{
            throw new NoSuchBookFoundException("No such book of this ISBN is present");
        }
    }

    public void addBook(Book book) {
        System.out.println(book+"bpoooo");
        bookRepository.save(book);
    }

    public boolean isBookTypePresent(String typeId){
        return bookRepository.existsById(typeId);
    }

    public List<Book> getAllBooks() {
        System.out.println(bookRepository.findAll()+"yoo");
        return bookRepository.findAll();
    }
}
