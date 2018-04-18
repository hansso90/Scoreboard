package nl.teamrockstars.chapter.east.scoreboard.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.factory.EntityObjectFactory;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;

@Mapper(uses = EntityObjectFactory.class, componentModel = "spring")
public interface ChapterMapper {

	ChapterDto toDto(Chapter entity);

	List<ChapterDto> toDtoList(List<Chapter> entity);

	@Mappings({//
			@Mapping(target = "id", ignore = true), //
			@Mapping(target = "lastModifiedAt", ignore = true), //
			@Mapping(target = "createdAt", ignore = true)//
	})
	Chapter fromDto(ChapterDto fromDto);
}
