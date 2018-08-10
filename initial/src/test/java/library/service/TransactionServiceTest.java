package library.service;


import library.Exception.InvalidTransactionTypeException;
import library.domain.TransactionRegister;
import library.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ReaderService readerService;

    public TransactionService transactionService;

    @Mock
    private StockService stockService;


    @Before
    public void setUp() throws Exception {
        transactionService = new TransactionService(transactionRepository, readerService, stockService);
    }

    @Test
    public void getByNameShouldReturnEmptyListWhenNoTransactionsArePresent() {
        List<TransactionRegister> transactions = transactionService.getByMemberId("Java");
        assertEquals(0,transactions.size());
    }

    @Test
    public void shouldReturnAllTheTransactionsOfParticularReader() {
        TransactionRegister transaction = new TransactionRegister("1234","JAVA1234",new Date(),new Date());
        when(transactionRepository.findByMemberId("1234")).thenReturn(Collections.singletonList(transaction));
        List<TransactionRegister> transactions = transactionService.getByMemberId("1234");
        assertEquals(transactions.size(),1);
    }

    @Test
    public void shouldAddTransactionIfTheUserIsOldUser() throws InvalidTransactionTypeException {
        TransactionRegister transaction = new TransactionRegister("1234","JAVA1234",new Date(),new Date());
        when(readerService.isValidReader(transaction.memberId)).thenReturn(true);
        when(stockService.isBookCopyAvailable(transaction.bookID)).thenReturn(true);
        transactionService.addTransaction(transaction);
        verify(transactionRepository,times(1)).save(transaction);
    }

    @Test(expected = InvalidTransactionTypeException.class)
    public void shouldNotAddTransactionIfTheUserIsUnregistered() throws InvalidTransactionTypeException {
        TransactionRegister transaction = new TransactionRegister("1234","JAVA1234",new Date(),null);
        when(readerService.isValidReader(transaction.memberId)).thenReturn(false);
        transactionService.addTransaction(transaction);
        verify(transactionRepository,times(0)).save(transaction);
    }

    @Test
    public void shouldReturnTheBook() {
        TransactionRegister transaction = new TransactionRegister("1234","JAVA1234",new Date(),null);
        when(transactionRepository.findByBookID("JAVA1234")).thenReturn(asList(transaction));
        transactionService.returnBook("JAVA1234",new Date());
        verify(transactionRepository,times(1)).save(transaction);
    }
}
