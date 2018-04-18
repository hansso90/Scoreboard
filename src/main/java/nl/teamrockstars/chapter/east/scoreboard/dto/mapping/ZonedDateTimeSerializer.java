package nl.teamrockstars.chapter.east.scoreboard.dto.mapping;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Converts a LocalDateTime to a ISO8601 compliant datetime, including timezone.
 * <p>
 * Timezone is defined by the System Default defined timezone, which should be AMS for Reggefiber servers.
 */
public class ZonedDateTimeSerializer extends StdSerializer<ZonedDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public ZonedDateTimeSerializer() {
        this(null);
    }

    public ZonedDateTimeSerializer(Class<ZonedDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(
            ZonedDateTime value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeString(fromZonedDateTime(value));
    }

    public static String fromZonedDateTime(ZonedDateTime instance) {

        return formatter.format(instance);
    }
}
