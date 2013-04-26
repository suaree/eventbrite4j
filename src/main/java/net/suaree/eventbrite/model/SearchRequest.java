package net.suaree.eventbrite.model;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request to the event_search API.
 *
 * @author roger
 */
public class SearchRequest extends RequestBase {
    /**
     * The keywords for the search.
     */
    private String[] keywords;

    /**
     * The categories for the search.
     */
    private Category[] categories;

    /**
     * The address for the search.
     */
    private String address;

    /**
     * The city for the search.
     */
    private String city;

    /**
     * The region for the search.
     */
    private String region;

    /**
     * The postal_code for the search.
     */
    private String postalCode;

    /**
     * The country for the search.
     */
    private String country;

    /**
     * The within radius for the search.
     */
    private Integer within;

    /**
     * The within_unit (radius unit) for the search.
     */
    private WithinUnit withinUnit;

    // TODO: latitude and longitude

    /**
     * The search_date for the search.
     */
    private SearchDate searchDate;

    // TODO: date_created and date_modified

    /**
     * The organizer for the search.
     */
    private String organizer;

    /**
     * The max (maximum number of results) for the search.
     */
    private Integer max;

    /**
     * The count_only for the search.
     */
    private Boolean countOnly;

    /**
     * The sort_by for the search.
     */
    private SortBy sortBy;

    /**
     * The page for the search.
     */
    private Integer page;

    /**
     * The since_id for the search.
     */
    private Long sinceId;

    /**
     * The tracking_link for the search.
     */
    private String trackingLink;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public void setAllCategories(Category... categories) {
        this.categories = categories;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean isCountOnly() {
        return countOnly;
    }

    public void setCountOnly(Boolean countOnly) {
        this.countOnly = countOnly;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public SearchDate getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(SearchDate searchDate) {
        this.searchDate = searchDate;
    }

    public Long getSinceId() {
        return sinceId;
    }

    public void setSinceId(Long sinceId) {
        this.sinceId = sinceId;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public String getTrackingLink() {
        return trackingLink;
    }

    public void setTrackingLink(String trackingLink) {
        this.trackingLink = trackingLink;
    }

    public Integer getWithin() {
        return within;
    }

    public void setWithin(Integer within) {
        this.within = within;
    }

    public WithinUnit getWithinUnit() {
        return withinUnit;
    }

    public void setWithinUnit(WithinUnit withinUnit) {
        this.withinUnit = withinUnit;
    }

    /**
     * Gets the name of the Eventbrite API this request is for.
     *
     * @return A string that represents the Eventbrite API name.
     */
    @Override
    protected String getApiName() {
        return "event_search";
    }

    /**
     * Gets the query parameters for this Eventbrite API request.
     *
     * @return A List of NameValuePair instances that define the request parameters.
     */
    @Override
    protected List<NameValuePair> getQueryParameters() {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>(32);

        if (null != keywords && 0 < keywords.length) {
            params.add(new BasicNameValuePair("keywords", getKeywordsParameter()));
        }

        if (null != categories && 0 < categories.length) {
            params.add(new BasicNameValuePair("category", convertToCommaSeparatedList(categories)));
        }

        addParameter("address", address, params);
        addParameter("city", city, params);
        addParameter("region", region, params);
        addParameter("postal_code", postalCode, params);
        addParameter("country", country, params);

        addParameter("within", within, params);
        addParameter("within_unit", withinUnit, params);

        addParameter("date", searchDate, params);

        addParameter("organizer", organizer, params);

        addParameter("max", max, params);
        addParameter("count_only", countOnly, params);
        addParameter("sort_by", sortBy, params);
        addParameter("page", page, params);
        addParameter("since_id", sinceId, params);

        addParameter("tracking_link", trackingLink, params);

        return params;
    }

    private String getKeywordsParameter() {
        StringBuilder sb = new StringBuilder();

        for (String keyword : keywords) {
            if (0 < sb.length()) {
                sb.append(" OR ");
            }

            sb.append(keyword);
        }

        return sb.toString();
    }
}
