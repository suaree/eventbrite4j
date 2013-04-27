package net.suaree.eventbrite.exception;

import net.suaree.eventbrite.model.Error;

/**
 * Represents an exception that wraps an error from the Eventbrite APIs.
 *
 * @author roger
 */
public class RequestErrorException extends RequestException {
    private final Error error;

    /**
     * Initializes a new instance of RequestErrorException, using the specified Error object.
     *
     * @param error The Error object describing the Eventbrite API request error.
     */
    public RequestErrorException(Error error) {
        if (null == error) {
            throw new IllegalArgumentException("error");
        }

        this.error = error;
    }

    /**
     * Initializes a new instance of RequestErrorException, using the specified cause and error objects.
     *
     * @param cause The cause of the exception
     * @param error The Error object describing the Eventbrite API request error.
     */
    public RequestErrorException(Throwable cause, Error error) {
        super(cause);

        if (null == error) {
            throw new IllegalArgumentException("error");
        }

        this.error = error;
    }

    /**
     * Gets the Error object that describes the Eventbrite API request error.
     *
     * @return An Error object describing the Eventbrite API request error.
     */
    public Error getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return String.format("%s - %s", error.getErrorType(), error.getErrorMessage());
    }
}
