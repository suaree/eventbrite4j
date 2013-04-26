package net.suaree.eventbrite.exception;

import net.suaree.eventbrite.model.ErrorResult;

/**
 * Represents an exception that wraps an error from the Eventbrite APIs.
 *
 * @author roger
 */
public class RequestErrorException extends RequestException {
    private final ErrorResult errorResult;

    public RequestErrorException(ErrorResult errorResult) {
        if (null == errorResult) {
            throw new IllegalArgumentException("errorResult");
        }

        this.errorResult = errorResult;
    }

    public RequestErrorException(Throwable cause, ErrorResult errorResult) {
        super(cause);

        if (null == errorResult) {
            throw new IllegalArgumentException("errorResult");
        }

        this.errorResult = errorResult;
    }

    public ErrorResult getErrorResult() {
        return errorResult;
    }

    @Override
    public String getMessage() {
        return String.format("%s - %s", errorResult.getErrorType(), errorResult.getErrorMessage());
    }
}
