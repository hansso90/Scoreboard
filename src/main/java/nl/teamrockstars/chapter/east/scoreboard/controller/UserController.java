package nl.teamrockstars.chapter.east.scoreboard.controller;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.dto.ActivityDto;
import nl.teamrockstars.chapter.east.scoreboard.dto.UserDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.UserMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.Activity;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import nl.teamrockstars.chapter.east.scoreboard.repository.UserRepository;

@RestController
@RequestMapping(value = PUBLIC + "/user")
@PreAuthorize("hasRole('USERMANAGEMENT')")
@Api(tags = "User controller", description = "Management of Users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
  	private UserMapper mapper;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get user", notes = "Gets a certain user with id", response = User.class)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<User>(userRepository.findById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "Get users", notes = "Gets all users", response = UserDto.class)
    public ResponseEntity<List<UserDto>> getUsers() {
    	
    	List<User> users = userRepository.findAllByOrderByName();
  		return new ResponseEntity<List<UserDto>>(mapper.userToUserDtoList(users), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "Create user", notes = "Create a new user")
    public HttpStatus userRole(@RequestBody User user) {
        //TODO: check content;
        userRepository.save(user);
        return HttpStatus.ACCEPTED;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "Update user", notes = "Update a ")
    public HttpStatus putUser(@RequestBody User user) {
        //TODO: check content;
        userRepository.save(user);
        return HttpStatus.ACCEPTED;
    }
}
