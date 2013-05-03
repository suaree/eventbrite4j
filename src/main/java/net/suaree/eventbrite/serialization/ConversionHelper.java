package net.suaree.eventbrite.serialization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Helper class for conversion of various values.
 *
 * @author roger
 */
public final class ConversionHelper {
    private static final Log log = LogFactory.getLog(ConversionHelper.class);

    private static final Pattern RegexCommaSeparatedList = Pattern.compile(",");

    private static final DateFormat DateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Returns the values of the given input array as a string with the values comma-separated.
     *
     * @param values The values to convert.
     * @param <T>    The type of values to convert.
     * @return A String that represents the comma-separated list of input values.
     */
    public static <T> String convertValuesToCommaSeparatedList(T[] values) {
        StringBuilder sb = new StringBuilder();

        for (T value : values) {
            if (0 < sb.length()) {
                sb.append(',');
            }

            sb.append(value);
        }

        return sb.toString();
    }

    /**
     * Returns a List of T objects that represent the enum values from the given comma-separated string.
     *
     * @param cls       The class of the Enum to return the list of values for.
     * @param rawValues The comma-separated string defining the values in the list.
     * @param <T>       The type of the Enum to return the list of values for.
     * @return A List of T objects that represent the enum values from the given comma-separated string.
     */
    public static <T extends Enum> List<T> convertCommaSeparatedListToEnumList(Class<T> cls, String rawValues) {
        if (null == rawValues) {
            return null;
        }

        String[] parts = RegexCommaSeparatedList.split(rawValues);
        HashMap<String, T> valueMap = new HashMap<String, T>();

        for (T value : cls.getEnumConstants()) {
            valueMap.put(value.toString().toLowerCase(Locale.US), value);
        }

        List<T> result = new ArrayList<T>(parts.length);

        for (String part : parts) {
            String partLower = part.toLowerCase(Locale.US);

            if (null == part || part.isEmpty()) {
                continue;
            }

            if (valueMap.containsKey(partLower)) {
                result.add(valueMap.get(partLower));
            } else {
                log.warn(String.format("Cannot find enum value for '%s'.", part));
            }
        }

        return result;
    }

    /**
     * Parses the given raw date/time spec (from an Eventbrite API) using the specified TimeZone information.
     *
     * @param rawDateTime The date/time spec to parse. Must be in the format "yyyy-MM-dd HH:mm:ss".
     * @param timeZone The TimeZone to parse the date/time spec in.
     * @return A new instance of Calendar that represents the parsed date/time spec in the specified TimeZone.
     */
    public static Calendar parseRawDateTime(String rawDateTime, TimeZone timeZone) {
        if (null == rawDateTime) {
            throw new IllegalArgumentException("rawDateTime");
        }

        if (null == timeZone) {
            throw new IllegalArgumentException("timeZone");
        }

        Calendar cal = Calendar.getInstance(timeZone);

        try {
            cal.setTime(DateTimeFormat.parse(rawDateTime));
        } catch (ParseException ex) {
            log.error("ParseException:", ex);

            throw new RuntimeException(ex);
        }

        return cal;
    }
}
