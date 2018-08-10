package library.Exception;

public class NoSuchBookFoundException extends Throwable {
    public NoSuchBookFoundException(String message) {
        super(message);
    }
}
