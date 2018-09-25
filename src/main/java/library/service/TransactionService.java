package library.service;

import library.Exception.BookNotAvailableException;
import library.Exception.InvalidTransactionTypeException;
import library.Exception.ReaderNotFoundException;
import library.domain.BookStock;
import library.domain.TransactionRegister;
import library.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private ReaderService readerService;
    private StockService stockService;

    public TransactionService(TransactionRepository transactionRepository, ReaderService readerService, StockService stockService) {

        this.transactionRepository = transactionRepository;
        this.readerService = readerService;
        this.stockService = stockService;
    }

    public List<TransactionRegister> getByMemberId(String memberId) {
        return transactionRepository.findByMemberId(memberId);
    }


    public void addTransaction(TransactionRegister transaction) throws InvalidTransactionTypeException {
        if(readerService.isValidReader(transaction.memberId) && stockService.isBookCopyAvailable(transaction.bookID)){
            transactionRepository.save(transaction);
        }
        else {
            throw new InvalidTransactionTypeException("Is not a registered reader");
        }
    }

    public void returnBook(String bookId, Date date) {
        List<TransactionRegister> transactions = transactionRepository.findByBookID(bookId);
        for (TransactionRegister transaction : transactions) {
            if(transaction.returnDate == null){
                transaction.setReturnDate(date);
                transactionRepository.save(transaction);
            }
        }
    }

    public String borrowBook(String userId, String isbn) throws ReaderNotFoundException, BookNotAvailableException {
        if (readerService.isValidReader(userId)){
            BookStock bookStock = stockService.borrowBookCopy(isbn);
            TransactionRegister transaction = new TransactionRegister(userId, bookStock.bookId, new Date(), new Date());
            transactionRepository.save(transaction);
            return "borrowed";
        }
        throw new ReaderNotFoundException("reader is not present");
    }
}
