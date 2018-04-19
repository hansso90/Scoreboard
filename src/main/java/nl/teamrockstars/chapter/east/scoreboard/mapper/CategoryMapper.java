package nl.teamrockstars.chapter.east.scoreboard.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import nl.teamrockstars.chapter.east.scoreboard.dto.CategoryDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.factory.EntityObjectFactory;
import nl.teamrockstars.chapter.east.scoreboard.model.Category;

@Mapper(uses = EntityObjectFactory.class, componentModel = "spring")
public interface CategoryMapper {

	CategoryDto toDto(Category entity);

	List<CategoryDto> toDtoList(List<Category> entity);

	@Mappings({//
			@Mapping(target = "id", ignore = true), //
			@Mapping(target = "lastModifiedAt", ignore = true), //
			@Mapping(target = "createdAt", ignore = true)//
	})
	Category fromDto(CategoryDto fromDto);
}
