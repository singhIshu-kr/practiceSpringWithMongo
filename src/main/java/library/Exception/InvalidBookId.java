package library.Exception;

public class InvalidBookId extends Throwable {
    public InvalidBookId(String message) {
        super(message);
    }
}
