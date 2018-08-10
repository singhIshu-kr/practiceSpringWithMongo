package library.Exception;

public class InvalidTransactionTypeException extends Throwable {
    public InvalidTransactionTypeException(String message) {
        super(message);
    }
}
