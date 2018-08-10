package library.service;

import library.Exception.NoSuchCopyFoundException;
import library.Exception.NotARegisteredBook;
import library.domain.BookStock;
import library.repository.StockRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private BookService bookService;

    private StockService stockService;

    @Before
    public void setUp() throws Exception {
        stockService = new StockService(stockRepository, bookService);
    }

    @Test
    public void getByNameShouldReturnEmptyListWhenNoBookIsPresent() {
        List<BookStock> books = stockService.getByTypeId("JAVA123");
        assertEquals(0,books.size());
    }

    @Test
    public void shouldReturnBookWhenBookIsPresent() {
        BookStock java = new BookStock("JAVA123",true);
        when(stockRepository.findByTypeId("JAVA123")).thenReturn(Collections.singletonList(java));
        List<BookStock> books = stockService.getByTypeId("JAVA123");
        assertEquals(books.size(),1);
    }

    @Test
    public void shouldRemoveTheBook() {
        stockService.removeBook("1234");
        verify(stockRepository,times(1)).deleteById("1234");
    }

    @Test
    public void shouldAddCopyOfBook() throws NotARegisteredBook {
        BookStock javaCopy = new BookStock("JAVA123", false);
        when(bookService.isBookTypePresent("JAVA123")).thenReturn(true);
        stockService.addBookCopy(javaCopy);
        verify(stockRepository,times(1)).save(javaCopy);
    }

    @Test(expected = NotARegisteredBook.class)
    public void shouldNotAddCopyIfSuchBookIsNotPresent() throws NotARegisteredBook {
        BookStock javaCopy = new BookStock("JAVA123", false);
        when(bookService.isBookTypePresent("JAVA123")).thenReturn(false);
        stockService.addBookCopy(javaCopy);
        verify(stockRepository,times(0)).save(javaCopy);
    }

    @Test
    public void shouldMakeCopyAvailable() throws NoSuchCopyFoundException {
        BookStock javaCopy = new BookStock("JAVA123", false);
        when(stockRepository.findById("JAVA123")).thenReturn(Optional.of(javaCopy));
        stockService.makeBookAvailable("JAVA123");
        verify(stockRepository,times(1)).save(javaCopy);
    }

    @Test(expected = NoSuchCopyFoundException.class)
    public void shouldThrowExceptionIfCopyDoesNotExists() throws NoSuchCopyFoundException {
        BookStock javaCopy = new BookStock("JAVA123", false);
        when(stockRepository.findById("JAVA123")).thenReturn(Optional.empty());
        stockService.makeBookAvailable("JAVA123");
        verify(stockRepository,times(0)).save(javaCopy);
    }
}