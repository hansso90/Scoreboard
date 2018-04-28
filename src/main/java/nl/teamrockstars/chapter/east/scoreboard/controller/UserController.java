package nl.teamrockstars.chapter.east.scoreboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.dto.UserDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.UserMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import nl.teamrockstars.chapter.east.scoreboard.repository.UserRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

@RestController
@RequestMapping(value = PUBLIC + "/user")
@PreAuthorize("hasRole('USERMANAGEMENT')")
@Api(tags = "User controller", description = "Management of Users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get user", notes = "Gets a certain user with id", response = User.class)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<User>(userRepository.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ApiOperation(value = "Get user", notes = "gets the current user out of token;", response = User.class)
    public ResponseEntity<UserDto> getCurrentUser() {
        return new ResponseEntity(mapper.userToUserDto(userService.getCurrentAuthentication()), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "Get users", notes = "Gets all users", response = UserDto.class)
    public ResponseEntity<List<UserDto>> getUsers() {

        List<User> users = userRepository.findAllByOrderByName();
        return new ResponseEntity<List<UserDto>>(mapper.userToUserDtoList(users), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "Create user", notes = "Create a new user")
    @PreAuthorize("hasAnyRole('USERMANAGEMENT', 'CHAPTERMANAGEMENT') ")
    public HttpStatus userRole(@RequestBody UserDto userDto, BindingResult errors) {
        Map<String, String> map = userService.validateAndSubmit(userDto, false);
        map.forEach( (index, text) -> errors.rejectValue(index, text));
        if(errors.hasErrors())
        {
            new MethodArgumentNotValidException(null, errors);
        }
        return HttpStatus.ACCEPTED;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "Update user", notes = "Update a user ")
    @PreAuthorize("hasAnyRole('USERMANAGEMENT', 'CHAPTERMANAGEMENT') ")
    public HttpStatus putUser(@RequestBody UserDto user, BindingResult errors) {
        Map<String, String> map = userService.validateAndSubmit(user, true);
        map.forEach( (index, text) -> errors.rejectValue(index, text));
        if(errors.hasErrors())
        {
            new MethodArgumentNotValidException(null, errors);
        }
        return HttpStatus.ACCEPTED;
    }
}
