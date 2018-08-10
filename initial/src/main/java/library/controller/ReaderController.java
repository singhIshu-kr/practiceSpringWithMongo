package library.controller;


import library.Exception.ReaderNotFoundException;
import library.domain.Reader;
import library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReaderController {

    private ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello Readers";
    }

    @GetMapping("/name/{name}")
    public List<Reader> getUserByName(@PathVariable String name){
        return readerService.getByName(name);
    }

    @GetMapping("/id/{id}")
    public Optional<Reader> getUserById(@PathVariable String id){
        return readerService.getByID(id);
    }

    @PostMapping("/addReader")
    public void addName(@RequestBody String firstName,@RequestBody String lastname){
        readerService.addReader(firstName,lastname);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestBody String userId) {
        readerService.removeReader(userId);
    }

    @PostMapping("/update")
    public void updateReader(@RequestBody String userId,@RequestBody String lastName) throws ReaderNotFoundException {
        readerService.updateReader(userId,lastName);
    }
}

