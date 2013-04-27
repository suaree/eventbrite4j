package net.suaree.eventbrite.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an error from the Eventbrite APIs.
 *
 * @author roger
 */
public class Error {
    @SerializedName("error_type")
    private String errorType;

    @SerializedName("error_message")
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }
}
