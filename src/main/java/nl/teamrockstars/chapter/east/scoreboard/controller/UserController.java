package nl.teamrockstars.chapter.east.scoreboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import nl.teamrockstars.chapter.east.scoreboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

@RestController
@RequestMapping(value = PUBLIC + "/user")
@PreAuthorize("hasRole('USERMANAGEMENT')")
@Api(tags = "User controller", description = "Management of Users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get user", notes = "Gets a certain user with id", response = User.class)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<User>(userRepository.findById(id), HttpStatus.OK);
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
