package net.suaree.eventbrite.model;

/**
 * Defines the event categories.
 *
 * @author roger
 */
public enum Category {
    Conferences("conferences"),
    Conventions("conventions"),
    Entertainment("entertainment"),
    Fundraisers("fundraisers"),
    Meetings("meetings"),
    Other("other"),
    Performances("performances"),
    Reunions("reunions"),
    Sales("sales"),
    Seminars("seminars"),
    Social("social"),
    Sports("sports"),
    Tradeshows("tradeshows"),
    Travel("travel"),
    Religion("religion"),
    Fairs("fairs"),
    Food("food"),
    Music("music"),
    Movies("movies"),
    Comedy("comedy"),
    Endurance("endurance"),
    Recreation("recreation");

    private final String value;

    private Category(String value) {
        this.value = value;
    }

    /**
     * Gets the category name as used by the Eventbrite APIs.
     *
     * @return A String representing the category name.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
