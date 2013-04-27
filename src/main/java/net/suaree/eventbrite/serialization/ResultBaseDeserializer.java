package net.suaree.eventbrite.serialization;

import com.google.gson.*;
import net.suaree.eventbrite.operations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Custom JsonDeserializer for classes derived from ResultBase.
 *
 * @author roger
 */
public class ResultBaseDeserializer implements JsonDeserializer<ResultBase> {
    private static final Log log = LogFactory.getLog(ResultBaseDeserializer.class);

    public ResultBase deserialize(JsonElement jsonElement,
                                  Type type,
                                  JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        for (Map.Entry<String, JsonElement> member : jsonObject.entrySet()) {
            if ("error".equals(member.getKey())) {
                return jsonDeserializationContext.deserialize(jsonElement, ErrorResult.class);
            } else if ("event".equals(member.getKey())) {
                return jsonDeserializationContext.deserialize(jsonElement, EventResult.class);
            } else if ("events".equals(member.getKey())) {
                return jsonDeserializationContext.deserialize(jsonElement, EventsResult.class);
            } else if ("venue".equals(member.getKey())) {
                return jsonDeserializationContext.deserialize(jsonElement, VenueResult.class);
            } else {
                log.warn(String.format("Unknown event search data type: '%s'.", member.getKey()));
            }
        }

        return null;
    }
}
