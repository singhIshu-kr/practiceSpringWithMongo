package library.domain;

import org.springframework.data.annotation.Id;

public class Book {
    @Id
    private String id;

    private String title;
    private String author;
    private String isbn;
    private int numberOfPages;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}