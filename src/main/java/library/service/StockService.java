package library.service;

import library.Exception.BookNotAvailableException;
import library.Exception.NoSuchCopyFoundException;
import library.Exception.NotARegisteredBook;
import library.domain.BookStock;
import library.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StockService {
    private final StockRepository stockRepository;
    private BookService bookService;

    @Autowired
    public StockService(StockRepository stockRepository, BookService bookService) {
        this.stockRepository = stockRepository;
        this.bookService = bookService;
    }
    public List<BookStock> getByTypeId(String typeId) {
        return stockRepository.findByTypeId(typeId);
    }

    public void removeBook(String isbnId) {
        stockRepository.deleteById(isbnId);
    }

    public void addBookCopy(BookStock bookStock) throws NotARegisteredBook {
        if(bookService.isBookTypePresent(bookStock.typeId)){
            stockRepository.save(bookStock);
        }
        else {
            throw new NotARegisteredBook("No such book of this type is present");
        }
    }

    public boolean isBookCopyAvailable(String bookID){
        return stockRepository.findById(bookID).get().availability;
    }

    public void makeBookAvailable(String bookID) throws NoSuchCopyFoundException {
        Optional<BookStock> copy = stockRepository.findById(bookID);
        if(copy.isPresent()){
            BookStock bookStock = copy.get();
            bookStock.setAvailability(true);
            stockRepository.save(bookStock);
        }
        else {
            throw new NoSuchCopyFoundException("No such copy present");
        }
    }

    public BookStock getAvailaibleCopy(String typeId) throws BookNotAvailableException {
        List<BookStock> byTypeId = stockRepository.findByTypeId(typeId);
        System.out.println(byTypeId+typeId);
        List<BookStock> availableBooks = byTypeId.stream().filter(bookCopy -> !bookCopy.availability).collect(Collectors.toList());
        System.out.println(availableBooks);
        if(availableBooks.size() != 0){
            return availableBooks.get(0);
        }
        throw new BookNotAvailableException("");
    }

    public List<BookStock> getAllBooks() {
        return stockRepository.findAll();
    }

    public void addBookCopies(String typeId, String noOfCopies) throws NotARegisteredBook {
        for (int i = 0; i < Integer.parseInt(noOfCopies); i++) {
            BookStock bookStock = new BookStock(typeId, false);
            addBookCopy(bookStock);
        }
    }

    public BookStock borrowBookCopy(String typeId) throws BookNotAvailableException {
        BookStock availaibleCopy = getAvailaibleCopy(typeId);
        System.out.println(availaibleCopy);
        availaibleCopy.setAvailability(true);
        stockRepository.save(availaibleCopy);
        return availaibleCopy;
    }
}
