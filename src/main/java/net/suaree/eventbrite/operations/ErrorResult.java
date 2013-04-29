package net.suaree.eventbrite.operations;

import net.suaree.eventbrite.model.Error;

/**
 * Represents an error result from a request to the Eventbrite APIs.
 *
 * @author roger
 */
public class ErrorResult extends ResultBase {
    private net.suaree.eventbrite.model.Error error;

    /**
     * Gets the Error for this ErrorResult.
     *
     * @return An instance of Error describing the error from the Eventbrite APIs.
     */
    public Error getError() {
        return error;
    }
}
