package library.controller;

import library.service.ReaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(ReaderController.class)
//public class MVCTest {
//
//    @MockBean
//    private ReaderService readerService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void shouldAddReaderWhenCalled() throws Exception {
//        mockMvc.perform(get("/name/ishu")).andExpect(status().isOk());
//
//        verify(readerService, times(1)).getByName("ishu");
//
//    }
//}
