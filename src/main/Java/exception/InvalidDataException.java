package exception;

/**
 * Created by Ник on 03.05.2016.
 */
public class InvalidDataException extends ExceptionDAO {
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(Throwable cause) {
        super(cause);
    }
}
