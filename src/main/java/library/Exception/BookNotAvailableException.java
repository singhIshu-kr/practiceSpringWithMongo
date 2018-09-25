package library.Exception;

public class BookNotAvailableException extends Throwable {
    public BookNotAvailableException(String message) {
        super(message);
    }
}
