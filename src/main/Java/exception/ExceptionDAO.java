package exception;

/**
 * Created by Ник on 25.04.2016.
 */
public class ExceptionDAO extends Exception {

    public ExceptionDAO(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionDAO(String message) {
        super(message);
    }

    public ExceptionDAO(Throwable cause) {
        super(cause);
    }

}

