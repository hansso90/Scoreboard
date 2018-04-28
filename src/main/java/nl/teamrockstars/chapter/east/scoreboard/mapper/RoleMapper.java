package nl.teamrockstars.chapter.east.scoreboard.mapper;

import nl.teamrockstars.chapter.east.scoreboard.dto.RoleDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.factory.EntityObjectFactory;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(uses = EntityObjectFactory.class, componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role entity);

    List<RoleDto> toDtoList(List<Role> entity);

    @Mappings({//
            @Mapping(target = "id", ignore = true), //
            @Mapping(target = "lastModifiedAt", ignore = true), //
            @Mapping(target = "createdAt", ignore = true)//
    })
    Role fromDto(RoleDto fromDto);
}
