package nl.teamrockstars.chapter.east.scoreboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.model.Activity;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

@RestController
@RequestMapping(value = PUBLIC + "/role")
@PreAuthorize("hasRole('ACTIVITYMANAGEMENT')")
@Api(tags = "Activity Controller", description = "Management of activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get activity", notes = "Gets a certain role with id", response = Activity.class)
    public ResponseEntity<Activity> getActivity(@PathVariable("id") Long id) {
        return new ResponseEntity<Activity>(activityRepository.findById(id), HttpStatus.OK);
    }
}
