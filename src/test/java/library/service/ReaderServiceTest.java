package library.service;

import library.Exception.ReaderNotFoundException;
import library.domain.Reader;
import library.repository.ReaderRepository;
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
public class ReaderServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    private library.service.ReaderService readerService;

    @Before
    public void setUp() throws Exception {
        readerService = new library.service.ReaderService(readerRepository);
    }

    @Test
    public void getByNameShouldReturnEmptyListWhenNoUserIsPresent() {
        List<Reader> readers = readerService.getByName("Ishu");
        assertEquals(0,readers.size());
    }

    @Test
    public void shouldReturnListOfReadersWhenManyReadersArePresent() {
        Reader ponu = new Reader("Ponu", "Singh");
        when(readerRepository.findByFirstName("Ponu")).thenReturn(Collections.singletonList(ponu));
        List<Reader> readers = readerService.getByName("Ponu");
        assertEquals(readers.size(),1);
    }

    @Test
    public void shouldDeleteTheReaderWhenManyReadersArePresent() {
        readerService.removeReader("12344");
        verify(readerRepository,times(1)).deleteById("12344");
    }

    @Test
    public void shouldUpdateTheReaderIfReaderIsPresent() throws ReaderNotFoundException {
        Reader reader = new Reader("Ponu", "Singh");
        when(readerRepository.findById("12344")).thenReturn(Optional.of(reader));
        readerService.updateReader("12344","Singh");
        verify(readerRepository,times(1)).save(reader);
    }

    @Test(expected = ReaderNotFoundException.class)
    public void shouldThrowExceptionIfReaderIsNotPresent() throws ReaderNotFoundException {
        Reader reader = new Reader("Ponu", "Singh");
        when(readerRepository.findById("12344")).thenReturn(Optional.empty());
        readerService.updateReader("12344","Singh");
        verify(readerRepository,times(0)).save(reader);
    }
}