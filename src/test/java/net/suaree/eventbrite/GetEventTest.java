package net.suaree.eventbrite;

import net.suaree.eventbrite.exception.RequestErrorException;
import net.suaree.eventbrite.exception.RequestException;
import net.suaree.eventbrite.model.*;
import net.suaree.eventbrite.model.Error;
import net.suaree.eventbrite.operations.EventResult;
import net.suaree.eventbrite.operations.GetEventRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Unit Tests for the EventbriteClient.get wrapper of event_get.
 *
 * @author roger
 */
public class GetEventTest extends TestBase {
    @Test
    public void testBasicError() {
        EventbriteClient client = new EventbriteClient(getCredentials(),
                new ResourceBasedHttpClient("/GetEvent-Error.json"));
        GetEventRequest request = new GetEventRequest();

        try {
            client.get(request);
            Assert.fail();
        } catch (RequestErrorException ex) {
            Error error = ex.getError();

            Assert.assertNotNull(error);
            Assert.assertEquals("Not Found", error.getErrorType());
            Assert.assertEquals("No records were found with the given parameters.", error.getErrorMessage());
        } catch (RequestException ex) {
            Assert.fail();
        }
    }

    @Test
    public void testGet() throws RequestException {
        EventbriteClient client = new EventbriteClient(getCredentials(),
                new ResourceBasedHttpClient("/GetEvent-Ok.json"));
        GetEventRequest request = new GetEventRequest();

        request.setId(5396196168L);

        EventResult result = client.get(request);
        Assert.assertNotNull(result);

        Event event = result.getEvent();
        Assert.assertNotNull(event);

        Assert.assertEquals("China, India, & U.S. Life Science Markets Symposium", event.getTitle());
        Assert.assertEquals(new Long(5396196168L), event.getId());
        Assert.assertEquals("http://chinaindiauslifesciencemarkets.eventbrite.com", event.getUrl());
        Assert.assertFalse(event.getRepeats().getBooleanValue());

        List<Category> categories = event.getCategories();
        Assert.assertNotNull(categories);
        Assert.assertEquals(2, categories.size());
        Assert.assertTrue(categories.contains(Category.Conferences));
        Assert.assertTrue(categories.contains(Category.Sales));

        Venue venue = event.getVenue();
        Assert.assertNotNull(venue);

        Assert.assertEquals(new Double(37.77493), venue.getLatitude());
        Assert.assertEquals(new Double(-122.419416), venue.getLongitude());

        Organizer organizer = event.getOrganizer();
        Assert.assertNotNull(organizer);

        Assert.assertEquals(new Long(2530262718L), organizer.getId());
        Assert.assertEquals("Center for Healthcare Innovation", organizer.getName());
        Assert.assertEquals("http://www.eventbrite.com/org/2530262718", organizer.getUrl());
    }
}
