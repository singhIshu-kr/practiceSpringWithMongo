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
    public Optional<Reader> getUserById(@PathVariable String id) throws ReaderNotFoundException {
        try {
            return readerService.getByID(id);
        }catch (ReaderNotFoundException e){
            return Optional.empty();
        }
    }

    @PostMapping("/addReader")
    public String addName(@RequestBody String firstName, @RequestBody String lastname){
        readerService.addReader(firstName,lastname);
        return "Reader is successfully added";
    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestBody String userId) {
        readerService.removeReader(userId);
        return "Reader is deleted";
    }

    @PostMapping("/update")
    public String updateReader(@RequestBody String userId, @RequestBody String lastName) throws ReaderNotFoundException {
        try {
            readerService.updateReader(userId,lastName);
            return "Successfully updated the lastName";
        } catch (ReaderNotFoundException e) {
            return e.getMessage();
        }
    }
}

