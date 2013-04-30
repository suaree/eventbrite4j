package net.suaree.eventbrite.serialization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Helper class for conversion of various values.
 *
 * @author roger
 */
public final class ConversionHelper {
    private static final Log log = LogFactory.getLog(ConversionHelper.class);

    private static final Pattern RegexCommaSeparatedList = Pattern.compile(",");

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
}
