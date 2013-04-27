package net.suaree.eventbrite;

/**
 * Represents access credentials for the Eventbrite APIs.
 *
 * @author roger
 */
public class Credentials {
    private final String appKey;

    private final String userKey;

    /**
     * Initializes a new instance of Credentials using the given appKey and no user key.
     *
     * @param appKey The value for the app_key parameter of the Eventbrite APIs.
     */
    public Credentials(String appKey) {
        this(appKey, null);
    }

    /**
     * Initializes a new instance of Credentials using the given appKey and userKey.
     *
     * @param appKey  The value for the app_key parameter of the Eventbrite APIs.
     * @param userKey The value for the user_key parameter of the Eventbrite APIs. This value is only used for APIs that
     *                require a user_key parameter.
     */
    public Credentials(String appKey, String userKey) {
        this.appKey = appKey;
        this.userKey = userKey;
    }

    /**
     * Gets the app_key value to use for the Eventbrite APIs.
     *
     * @return A String that represents the app_key value for the Eventbrite APIs.
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * Gets the user_key value to use for the Eventbrite APIs.
     *
     * @return A String that represents the user_key value for the Eventbrite APIs.
     */
    public String getUserKey() {
        return userKey;
    }
}
