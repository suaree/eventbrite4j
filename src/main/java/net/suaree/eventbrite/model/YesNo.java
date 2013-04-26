package net.suaree.eventbrite.model;

/**
 * Represents boolean (yes/no) values for the Eventbrite APIs.
 *
 * @author roger
 */
public enum YesNo {
    Yes("yes", true),
    No("no", false);

    private final boolean booleanValue;
    private final String value;

    private YesNo(String value, boolean booleanValue) {
        this.value = value;
        this.booleanValue = booleanValue;
    }

    public String getValue() {
        return value;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    @Override
    public String toString() {
        return value;
    }
}
