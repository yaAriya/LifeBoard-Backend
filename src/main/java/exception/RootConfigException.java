package exception;

public class RootConfigException extends  Exception {
    public RootConfigException() {
        super();
    }

    public RootConfigException(String message) {
        super(message);
    }

    public RootConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public RootConfigException(Throwable cause) {
        super(cause);
    }

    protected RootConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
