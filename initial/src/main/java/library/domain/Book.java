package library.domain;

import org.springframework.data.annotation.Id;

public class Book {
    @Id
    private String isbn;

    private String title;
    private String author;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}