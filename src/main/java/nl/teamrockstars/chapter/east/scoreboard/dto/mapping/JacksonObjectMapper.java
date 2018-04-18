package nl.teamrockstars.chapter.east.scoreboard.dto.mapping;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonObjectMapper
{
        public static ObjectMapper buildObjectMapper() {
        
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        ObjectMapper objectMapper = builder.build();
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer( ));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer( ));
        module.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer( ));
        module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer( ));
        objectMapper.registerModule(module);
        
        return objectMapper;
    }
}
