package nl.teamrockstars.chapter.east.scoreboard.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import nl.teamrockstars.chapter.east.scoreboard.dto.ActivityDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.factory.EntityObjectFactory;
import nl.teamrockstars.chapter.east.scoreboard.model.Activity;

@Mapper( uses = {EntityObjectFactory.class, UtilMapper.class, UserMapper.class}, componentModel = "spring" )
public interface ActivityMapper {

  ActivityDto toDto(Activity entity);
  
  List<ActivityDto> toDtoList(List<Activity> entity);

  @Mapping(target = "id", ignore = true)
  Activity fromDto(ActivityDto fromDto);
}
