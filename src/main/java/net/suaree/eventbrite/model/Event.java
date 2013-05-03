package net.suaree.eventbrite.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import net.suaree.eventbrite.serialization.ConversionHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Represents an event as retrieved through the event_search API.
 *
 * @author roger
 */
public class Event extends EventData {
    private static final Log log = LogFactory.getLog(Event.class);

    private Long id;

    @SerializedName("category")
    private String categoriesRaw;

    private List<Category> categories;

    private String title;

    @SerializedName("timezone")
    private TimeZone timeZone;

    @SerializedName("start_date")
    private String startDateRaw;

    @SerializedName("end_date")
    private String endDateRaw;

    private YesNo repeats;

    @SerializedName("repeat_schedule")
    private String repeatScheduleRaw;

    private String url;

    private Venue venue;

    private String tags;

    private String description;

    private Privacy privacy;

    private EventStatus status;

    private long capacity;

    @SerializedName("num_attendee_rows")
    private long numAttendees;

    private Organizer organizer;

    private List<TicketWrapper> tickets;

    @SerializedName("created")
    private String createdRaw;

    @SerializedName("modified")
    private String modifiedRaw;

    public static Type getType() {
        return TypeToken.get(Event.class).getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Category> getCategories() {
        if (null == categories) {
            categories = ConversionHelper.convertCommaSeparatedListToEnumList(Category.class, categoriesRaw);
        }

        return categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getStartDateRaw() {
        return startDateRaw;
    }

    public Calendar getStartDate() {
        return ConversionHelper.parseRawDateTime(startDateRaw, timeZone);
    }

    public String getEndDateRaw() {
        return endDateRaw;
    }

    public Calendar getEndDate() {
        return ConversionHelper.parseRawDateTime(endDateRaw, timeZone);
    }

    public long getDurationInMillis() {
        return getEndDate().getTimeInMillis() - getStartDate().getTimeInMillis();
    }

    public YesNo getRepeats() {
        return repeats;
    }

    public void setRepeats(YesNo repeats) {
        this.repeats = repeats;
    }

    public String getRepeatScheduleRaw() {
        return repeatScheduleRaw;
    }

    public RepeatSchedule getRepeatSchedule() {
        return RepeatSchedule.parse(timeZone, repeatScheduleRaw);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getNumAttendees() {
        return numAttendees;
    }

    public void setNumAttendees(long numAttendees) {
        this.numAttendees = numAttendees;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public List<? extends Ticket> getTickets() {
        return TicketWrapper.getTickets(tickets);
    }

    public EventStatus getStatus() {
        return status;
    }

    public String getCreatedRaw() {
        return createdRaw;
    }

    public Calendar getCreated() {
        return ConversionHelper.parseRawDateTime(createdRaw, timeZone);
    }

    public String getModifiedRaw() {
        return modifiedRaw;
    }

    public Calendar getModified() {
        return ConversionHelper.parseRawDateTime(modifiedRaw, timeZone);
    }
}
