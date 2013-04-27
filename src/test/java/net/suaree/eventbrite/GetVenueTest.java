package net.suaree.eventbrite;

import net.suaree.eventbrite.exception.RequestErrorException;
import net.suaree.eventbrite.exception.RequestException;
import net.suaree.eventbrite.model.Venue;
import net.suaree.eventbrite.operations.GetVenueRequest;
import net.suaree.eventbrite.operations.VenueResult;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author roger
 */
public class GetVenueTest extends TestBase {
    @Test
    public void testBasicError() {
        EventbriteClient client = new EventbriteClient(getCredentials(),
                new ResourceBasedHttpClient("/GetVenue-Error.json"));
        GetVenueRequest request = new GetVenueRequest();

        try {
            client.get(request);
            Assert.fail();
        } catch (RequestErrorException ex) {
            net.suaree.eventbrite.model.Error error = ex.getError();

            Assert.assertNotNull(error);
            Assert.assertEquals("Venue error", error.getErrorType());
            Assert.assertEquals("The venue ID is missing.", error.getErrorMessage());
        } catch (RequestException ex) {
            Assert.fail();
        }
    }

    @Test
    public void testMissingUserKey() {
        EventbriteClient client = new EventbriteClient(new Credentials("invalid"));
        GetVenueRequest request = new GetVenueRequest();

        try {
            client.get(request);
            Assert.fail();
        } catch (RequestErrorException ex) {
            Assert.fail();
        } catch (RequestException ex) {
            Assert.assertTrue(ex.getMessage().startsWith("A user key is required "));
        }
    }

    @Test
    public void testGet() throws RequestException {
        EventbriteClient client = new EventbriteClient(getCredentials(),
                new ResourceBasedHttpClient("/GetVenue-Ok.json"));
        GetVenueRequest request = new GetVenueRequest();

        request.setId(1524221L);

        VenueResult result = client.get(request);
        Assert.assertNotNull(result);

        Venue venue = result.getFirstVenue();
        Assert.assertNotNull(venue);

        Assert.assertEquals(new Long(1524221), venue.getId());
    }
}
