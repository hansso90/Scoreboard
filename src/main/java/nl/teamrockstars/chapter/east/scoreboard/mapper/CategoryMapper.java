package nl.teamrockstars.chapter.east.scoreboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import nl.teamrockstars.chapter.east.scoreboard.dto.CategoryDto;
import nl.teamrockstars.chapter.east.scoreboard.model.Category;

@Mapper
public interface CategoryMapper {

  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  CategoryDto categoryToCategoryDto(Category category);
  
  Category categoryDtoToCategory(CategoryDto category);
}
