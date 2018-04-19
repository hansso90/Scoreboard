package nl.teamrockstars.chapter.east.scoreboard.dto.mapping;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static final ZonedDateTimeDeserializer INSTANCE = new ZonedDateTimeDeserializer();

    public ZonedDateTimeDeserializer() {
        this(null);
    }

    public ZonedDateTimeDeserializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        String s = jsonParser.getValueAsString();
        return fromISO8601(s);
    }

    public static ZonedDateTime fromISO8601(String value) {

        return ZonedDateTime.parse(value, formatter);
    }

}
