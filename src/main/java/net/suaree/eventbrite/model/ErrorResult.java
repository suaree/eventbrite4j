package net.suaree.eventbrite.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author roger
 */
public class ErrorResult extends ResultBase {
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
