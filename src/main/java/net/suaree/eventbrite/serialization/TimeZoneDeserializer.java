package net.suaree.eventbrite.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.TimeZone;

/**
 * Custom JsonDeserializer for TimeZone objects.
 *
 * @author roger
 */
public class TimeZoneDeserializer implements JsonDeserializer<TimeZone> {
    public TimeZone deserialize(JsonElement jsonElement,
                                Type type,
                                JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        String timezone = jsonElement.getAsString();

        return TimeZone.getTimeZone(timezone);
    }
}
