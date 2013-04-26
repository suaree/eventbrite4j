package net.suaree.eventbrite.serialization;

import com.google.gson.*;
import net.suaree.eventbrite.model.Event;
import net.suaree.eventbrite.model.EventData;
import net.suaree.eventbrite.model.EventSummaryData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Custom JsonDeserializer for classes derived from EventData.
 *
 * @author roger
 */
public class EventSearchDataDeserializer implements JsonDeserializer<EventData> {
    private static final Log log = LogFactory.getLog(EventSearchDataDeserializer.class);

    public EventData deserialize(JsonElement jsonElement,
                                 Type type,
                                 JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        for (Map.Entry<String, JsonElement> member : jsonObject.entrySet()) {
            if ("summary".equals(member.getKey())) {
                return jsonDeserializationContext.deserialize(member.getValue(), EventSummaryData.getType());
            } else if ("event".equals(member.getKey())) {
                return jsonDeserializationContext.deserialize(member.getValue(), Event.getType());
            } else {
                log.warn(String.format("Unknown event search data type: '%s'.", member.getKey()));
            }
        }

        return null;
    }
}
