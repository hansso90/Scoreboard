package nl.teamrockstars.chapter.east.scoreboard.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nl.teamrockstars.chapter.east.scoreboard.dto.UserDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.UserMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import nl.teamrockstars.chapter.east.scoreboard.repository.RoleRepository;
import nl.teamrockstars.chapter.east.scoreboard.repository.UserRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.ChapterService;
import nl.teamrockstars.chapter.east.scoreboard.service.UserService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserMapper mapper;

    @Autowired
    private ChapterService chapterService;
    
    @Override
    public User findOrCreate(String username, String name) {
    	
    	User user = userRepository.findByUsername(username);
    	if(user == null) {
    		Role role = roleRepository.findOne(new Long(1));
    		user = createNewUser(username, null, name, role, null);
    	}
    	user.setName(name);
    	
    	return user;
    }

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

    @Override
    public UserDto submit(UserDto dto) {
        User user = mapper.fromDto(dto);

        userRepository.save(user);
        return mapper.userToUserDto(user);
    }

    @Override
    public Map<String, String> validate(UserDto userDto, Boolean alreadyExists) {
        Map<String, String> map = new HashMap();
        map.putAll(chapterService.validate(userDto.getChapter(), true));
        return map;
    }

}
