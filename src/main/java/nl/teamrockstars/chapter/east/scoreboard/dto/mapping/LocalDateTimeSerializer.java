package nl.teamrockstars.chapter.east.scoreboard.dto.mapping;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Converts a LocalDateTime to a ISO8601 compliant datetime, including timezone.
 * <p>
 * Timezone is defined by the System Default defined timezone, which should be AMS for Reggefiber servers.
 */
public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public LocalDateTimeSerializer() {
        this(null);
    }

    public LocalDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(
            LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeString(fromLocalDateTime(value));
    }

    public static String fromLocalDateTime(LocalDateTime instance) {

        ZonedDateTime t = ZonedDateTime.of(instance.toLocalDate(), instance.toLocalTime(), ZoneId.systemDefault());
        return formatter.format(t);
    }
}
