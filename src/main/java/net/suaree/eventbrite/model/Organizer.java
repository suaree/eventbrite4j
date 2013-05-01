package net.suaree.eventbrite.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an organizer of events.
 *
 * @author roger
 */
public class Organizer {
    private Long id;

    private String name;

    private String description;

    @SerializedName("long_description")
    private String longDescription;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
