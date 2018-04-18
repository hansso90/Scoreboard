package nl.teamrockstars.chapter.east.scoreboard.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class UtilMapper {

	private static final ZoneId UTC_ZONE = ZoneId.of("UTC");

	ZonedDateTime from(LocalDateTime ldt) {

	    if(ldt == null) {
	        return null;
	    }
		return ldt.atZone(UTC_ZONE);
	}

	LocalDateTime from(ZonedDateTime zdt) {
	    if(zdt == null) {
	        return null;
	    }
		return zdt.withZoneSameInstant(UTC_ZONE).toLocalDateTime();
	}
}
