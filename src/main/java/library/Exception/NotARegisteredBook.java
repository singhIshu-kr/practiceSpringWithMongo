package library.Exception;

public class NotARegisteredBook extends Throwable {
    public NotARegisteredBook(String message) {
        super(message);
    }
}
