package nl.teamrockstars.chapter.east.scoreboard.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import nl.teamrockstars.chapter.east.scoreboard.dto.UserDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.factory.EntityObjectFactory;
import nl.teamrockstars.chapter.east.scoreboard.model.User;

@Mapper(uses = {EntityObjectFactory.class, UtilMapper.class}, componentModel = "spring")
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mappings({
      @Mapping(target = "role", source = "role.name"),
      @Mapping(target = "rights", source = "role.rights"),
      @Mapping(target = "chapter", source = "chapter.name")
  })
  UserDto userToUserDto(User user);
  
  List<UserDto> userToUserDtoList(List<User> user);
}
