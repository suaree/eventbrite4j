package net.suaree.eventbrite.model;

/**
 * Defines the event categories.
 *
 * @author roger
 */
public enum Display {
    CustomHeader("custom_header"),
    CustomFooter("custom_footer"),
    ConfirmationPage("confirmation_page"),
    ConfirmationEmail("confirmation_email");

    private final String value;

    private Display(String value) {
        this.value = value;
    }

    /**
     * Gets the display name as used by the Eventbrite APIs.
     *
     * @return A String representing the display name.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
