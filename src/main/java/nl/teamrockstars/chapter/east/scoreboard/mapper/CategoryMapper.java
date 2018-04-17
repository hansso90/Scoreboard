package nl.teamrockstars.chapter.east.scoreboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import nl.teamrockstars.chapter.east.scoreboard.dto.CategoryDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.factory.EntityObjectFactory;
import nl.teamrockstars.chapter.east.scoreboard.model.Category;

@Mapper( uses = EntityObjectFactory.class, componentModel = "spring" )
public interface CategoryMapper {

  CategoryDto toDto(Category category);

  @Mapping(target = "id", ignore = true)
  Category fromDto(CategoryDto fromDto);
}
