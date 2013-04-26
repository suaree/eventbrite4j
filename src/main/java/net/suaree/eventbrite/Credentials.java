package net.suaree.eventbrite;

/**
 * Represents access credentials for the Eventbrite APIs.
 *
 * @author roger
 */
public class Credentials {
    private final String appKey;

    /**
     * Initializes a new instance of Credentials using the given appKey.
     *
     * @param appKey The value for the app_key parameter of the Eventbrite APIs.
     */
    public Credentials(String appKey) {
        this.appKey = appKey;
    }

    /**
     * Gets the app_key value to use for the Eventbrite APIs.
     *
     * @return A String that represents the app_key value for the Eventbrite APIs.
     */
    public String getAppKey() {
        return appKey;
    }
}
