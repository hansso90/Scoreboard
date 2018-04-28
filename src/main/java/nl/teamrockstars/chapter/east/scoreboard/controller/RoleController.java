package nl.teamrockstars.chapter.east.scoreboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.dto.RoleDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.RoleMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

@RestController
@RequestMapping(value = PUBLIC + "/role")
@PreAuthorize("hasRole('ROLEMANAGEMENT')")
@Api(tags = "Role Controller", description = "Management of roles")
public class RoleController {

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private RoleMapper mapper;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ApiOperation(value = "Get role", notes = "Gets a certain role with id", response = Role.class)
  public ResponseEntity<RoleDto> getRole(@PathVariable("id") Long id) {
    Role role = roleRepository.findOne(id);
    if (role == null) {

      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(mapper.toDto(role), HttpStatus.OK);
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  @ApiOperation(value = "Get all roles", notes = "Get all roles", response = Role.class)
  public ResponseEntity<List<RoleDto>> getRoles() {
    List<Role> roles = roleRepository.findAllByOrderByName();
    return new ResponseEntity(mapper.toDtoList(roles), HttpStatus.OK);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  @ApiOperation(value = "Create role", notes = "Create a new role")
  public HttpStatus postRole(@RequestBody Role role) {

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
