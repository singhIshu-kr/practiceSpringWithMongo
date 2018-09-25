package library.domain;


import org.springframework.data.annotation.Id;

public class Book {

    @Id
    public String isbn;
    public String title;
    public String author;


    public Book(String title, String author,String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
}