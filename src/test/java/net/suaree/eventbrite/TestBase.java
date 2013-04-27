package net.suaree.eventbrite;

/**
 * @author roger
 */
abstract class TestBase {
    private final Credentials credentials = new Credentials("EHHWMU473LTVEO4JFY", "invalid-user-key"); // Eventbrite's Test Key

    protected Credentials getCredentials() {
        return credentials;
    }
}
