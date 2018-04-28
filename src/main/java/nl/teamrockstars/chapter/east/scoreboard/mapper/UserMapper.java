package nl.teamrockstars.chapter.east.scoreboard.mapper;

import nl.teamrockstars.chapter.east.scoreboard.dto.UserDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.factory.EntityObjectFactory;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {EntityObjectFactory.class, UtilMapper.class}, componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    List<UserDto> userToUserDtoList(List<User> user);

    @Mappings({//
            @Mapping(target = "lastModifiedAt", ignore = true), //
            @Mapping(target = "createdAt", ignore = true)//
    })
    User fromDto(UserDto fromDto);
}
