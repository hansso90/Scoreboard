package nl.teamrockstars.chapter.east.scoreboard.service.impl;

import java.time.LocalDateTime;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import nl.teamrockstars.chapter.east.scoreboard.repository.UserRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
    }
    return user;
  }

  @Override
  public void checkPassword(User user, String rawPassword) throws BadCredentialsException {
    String encodedPassword = user.getPassword();
    if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
      throw new BadCredentialsException("Wachtwoord komt niet overeen.");
    }
  }

  @Override
  public User getCurrentAuthentication() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public User createNewUser(String username, String password, String name, Role role,
      Chapter chapter) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setName(name);
    user.setRole(role);
    user.setChapter(chapter);
    user.setEnabled(true);
    user.setExpired(LocalDateTime.now().plusDays(30));
    user.setLocked(false);
    userRepository.save(user);
    return user;
  }
}
