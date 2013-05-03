package net.suaree.eventbrite.model;

import com.google.gson.annotations.SerializedName;
import net.suaree.eventbrite.serialization.ConversionHelper;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Represents ticket information for an event in the Eventbrite APIs.
 *
 * @author roger
 */
public class Ticket {
    private Long id;

    private String name;

    private String description;

    private TicketType type;

    @SerializedName("max")
    private Long maxQuantityPerOrder;

    @SerializedName("min")
    private Long minQuantityPerOrder;

    private String currency;

    @SerializedName("price")
    private String priceRaw;

    @SerializedName("start_date")
    private String startDateRaw;

    @SerializedName("end_date")
    private String endDateRaw;

    @SerializedName("quantity_available")
    private Long quantityAvailable;

    @SerializedName("quantity_sold")
    private Long quantitySold;

    @SerializedName("visible")
    private String visibleRaw;


    public Long getId() {
        return id;
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

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Long getMaxQuantityPerOrder() {
        return maxQuantityPerOrder;
    }

    public void setMaxQuantityPerOrder(Long maxQuantityPerOrder) {
        this.maxQuantityPerOrder = maxQuantityPerOrder;
    }

    public Long getMinQuantityPerOrder() {
        return minQuantityPerOrder;
    }

    public void setMinQuantityPerOrder(Long minQuantityPerOrder) {
        this.minQuantityPerOrder = minQuantityPerOrder;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getPrice() {
        return Float.parseFloat(priceRaw);
    }

    public Calendar getStartDate(TimeZone timeZone) {
        return ConversionHelper.parseRawDateTime(startDateRaw, timeZone);
    }

    public Calendar getEndDate(TimeZone timeZone) {
        return ConversionHelper.parseRawDateTime(endDateRaw, timeZone);
    }

    public Long getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Long quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Long getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Long quantitySold) {
        this.quantitySold = quantitySold;
    }

    public boolean isVisible() {
        return Boolean.parseBoolean(visibleRaw);
    }
}
