package nl.teamrockstars.chapter.east.scoreboard.mapper;


import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.Arrays;
import nl.teamrockstars.chapter.east.scoreboard.dto.UserDto;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import nl.teamrockstars.chapter.east.scoreboard.model.Right;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import org.junit.Test;

public class UserMapperTest {

  @Test
  public void shouldMapUserToDto() {

    Chapter chapter = new Chapter();
    chapter.setName("East");

    Role role = new Role();
    role.setName("Admin");
    role.setRights(Arrays.asList(Right.values()));

    //given
    User user = new User();
    user.setName("John Doe");
    user.setRole(role);
    user.setChapter(chapter);

    //when
    UserDto userDto = UserMapper.INSTANCE.userToUserDto( user );

    //then
    assertThat( userDto ).isNotNull();
    assertThat( userDto.getName() ).isEqualTo( "John Doe" );
    assertThat( userDto.getRole() ).isEqualTo( "Admin" );
    assertThat( userDto.getRights().size() ).isEqualTo( Arrays.asList(Right.values()).size() );
    assertThat( userDto.getChapter() ).isEqualTo( "East" );
  }

}