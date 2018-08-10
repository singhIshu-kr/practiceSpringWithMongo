package library.domain;

import org.springframework.data.annotation.Id;


public class BookStock {
    @Id
    private String bookId;

    public String typeId;
    public Boolean availability;


    public BookStock(String typeId,Boolean availability) {
        this.typeId = typeId;
        this.availability = availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
