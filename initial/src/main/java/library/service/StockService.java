package library.service;

import library.Exception.NoSuchCopyFoundException;
import library.Exception.NotARegisteredBook;
import library.domain.BookStock;
import library.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
        return stockRepository.findById(bookID).get().availability == true;
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
}
