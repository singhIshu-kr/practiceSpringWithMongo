package library.service;

import library.Exception.InvalidTransactionTypeException;
import library.domain.TransactionRegister;
import library.repository.TransactionRepository;

import java.util.Date;
import java.util.List;

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
}
