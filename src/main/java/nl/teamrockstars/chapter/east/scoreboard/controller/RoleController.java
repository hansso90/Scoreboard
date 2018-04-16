package nl.teamrockstars.chapter.east.scoreboard.controller;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.repository.RoleRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = PUBLIC + "/role")
@PreAuthorize("hasRole('ROLEMANAGEMENT')")
@Api(tags = "Role Controller", description = "Management of roles")
public class RoleController {

  @Autowired
  private RoleRepository roleRepository;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ApiOperation(value = "Get role", notes = "Gets a certain role with id", response = Role.class)
  public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {
    return new ResponseEntity<Role>(roleRepository.findById(id), HttpStatus.OK);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  @ApiOperation(value = "Create role", notes = "Create a new role")
  public HttpStatus postRole(@RequestBody Role role) {
    //TODO: check content;
    roleRepository.save(role);
    return HttpStatus.ACCEPTED;
  }

  @RequestMapping(value = "", method = RequestMethod.PUT)
  @ApiOperation(value = "Create role", notes = "Create a new role")
  public HttpStatus putRole(@RequestBody Role role) {
    //TODO: check content;
    roleRepository.save(role);
    return HttpStatus.ACCEPTED;
  }
}
