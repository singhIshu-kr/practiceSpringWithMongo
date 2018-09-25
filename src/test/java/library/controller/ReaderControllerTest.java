package library.controller;

import library.Exception.ReaderNotFoundException;
import library.service.ReaderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class ReaderControllerTest {

    @Mock
    private ReaderService readerService;

    private library.controller.ReaderController readerController;


    @Before
    public void setUp() throws Exception {
        readerController = new library.controller.ReaderController(readerService);
    }

    @Test
    public void getUserByName() {
        readerController.getUserByName("ishu");
        verify(readerService,times(1)).getByName("ishu");
    }

    @Test
    public void shouldCallAddReaderFromTheReaderService() {
        ReqBody reqBody = new ReqBody();
        reqBody.firstName = "Ponu";
        reqBody.lastName = "Singh";
        readerController.addName(reqBody);
        verify(readerService,times(1)).addReader("Ponu","Singh");
    }

    @Test
    public void shouldCallRemoveReaderFromTheReaderService() {
        readerController.deleteById("12345");
        verify(readerService,times(1)).removeReader("12345");
    }

//    @Test
//    public void shouldCallUpdateReaderFromTheReaderService() throws ReaderNotFoundException {
//        readerController.updateReader("123456","Ponu");
//        verify(readerService,times(1)).updateReader("123456","Ponu");
//    }

    @Test
    public void shouldCallGetByIdFromTheReaderService() throws ReaderNotFoundException {
        readerController.getUserById("12345");
        verify(readerService,times(1)).getByID("12345");
    }
}
