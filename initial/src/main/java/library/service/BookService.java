package library.service;

import library.Exception.NoSuchBookFoundException;
import library.domain.Book;
import library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        bookRepository.save(book);
    }

    public boolean isBookTypePresent(String typeId){
        return bookRepository.existsById(typeId);
    }

}
