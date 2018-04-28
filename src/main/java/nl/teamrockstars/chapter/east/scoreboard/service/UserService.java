package nl.teamrockstars.chapter.east.scoreboard.service;

import nl.teamrockstars.chapter.east.scoreboard.dto.UserDto;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface UserService extends UserDetailsService {

    User getCurrentAuthentication();

    User createNewUser(String username, String password, String name, Role role, Chapter chapter);

    void checkPassword(User user, String rawPassword) throws BadCredentialsException;

    Map<String, String> validateAndSubmit(UserDto userDto, Boolean alreadyExist);
}
