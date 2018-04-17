package nl.teamrockstars.chapter.east.scoreboard.mapper;

import nl.teamrockstars.chapter.east.scoreboard.dto.UserDto;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mappings({
      @Mapping(target = "role", source = "role.name"),
      @Mapping(target = "rights", source = "role.rights"),
      @Mapping(target = "chapter", source = "chapter.name")
  })
  UserDto userToUserDto(User user);
}
