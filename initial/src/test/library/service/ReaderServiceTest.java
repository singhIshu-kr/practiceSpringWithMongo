package library.service;

import library.domain.Reader;
import library.repository.ReaderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ReaderServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    private ReaderService readerService;

    @Before
    public void setUp() throws Exception {
        readerService = new ReaderService(readerRepository);
    }

    @Test
    public void getByNameShouldReturnEmptyListWhenNoUserIsPresent() {
        List<Reader> readers = readerService.getByName("Ishu");
        assertEquals(0,readers.size());
    }

    @Test
    public void shouldReturnListOfReadersWhenManyReadersArePresent() {
        Reader ponu = new Reader("Ponu", "Singh");
        Reader rahul = new Reader("Rahul", "Patil");
        when(readerRepository.findByFirstName("Ponu")).thenReturn(Collections.singletonList(ponu));
        List<Reader> readers = readerService.getByName("Ponu");
        assertEquals(readers.size(),1);
    }
}