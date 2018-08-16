package library.service;

import library.Exception.NoSuchBookFoundException;
import library.domain.Book;
import library.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private library.service.BookService bookService;

    @Before
    public void setUp() throws Exception {
        bookService = new library.service.BookService(bookRepository);
    }

    @Test
    public void getByNameShouldReturnEmptyListWhenNoBookIsPresent() {
        List<Book> books = bookService.getByName("Java");
        assertEquals(0,books.size());
    }

    @Test
    public void shouldReturnBookWhenBookIsPresent() {
        Book java = new Book("Java","VK");
        when(bookRepository.findByTitle("Java")).thenReturn(Collections.singletonList(java));
        List<Book> books = bookService.getByName("Java");
        assertEquals(books.size(),1);
    }

    @Test
    public void shouldRemoveTheBookIfTheBookIsPresent() throws NoSuchBookFoundException {
        when(bookRepository.existsById("1234")).thenReturn(true);
        bookService.removeBook("1234");
        verify(bookRepository,times(1)).deleteById("1234");
    }

    @Test(expected = NoSuchBookFoundException.class)
    public void shouldThrowExceptionIfBookIsNotPresent () throws NoSuchBookFoundException {
        when(bookRepository.existsById("1234")).thenReturn(false);
        bookService.removeBook("1234");
        verify(bookRepository,times(1)).deleteById("1234");
    }

    @Test
    public void shouldAddBook() {
        Book book = new Book("Lilah", "Gulzar");
        bookService.addBook(book);
        verify(bookRepository,times(1)).save(book);
    }

    @Test
    public void shouldReturnTrueIfBookTypeExists() {
        when(bookRepository.existsById("1234")).thenReturn(true);
        bookService.isBookTypePresent("1234");
        verify(bookRepository,times(1)).existsById("1234");
    }
}