package exception;

public class CardServiceException extends Exception {
    public CardServiceException() {
        super();
    }

    public CardServiceException(String message) {
        super(message);
    }

    public CardServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardServiceException(Throwable cause) {
        super(cause);
    }

    protected CardServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
