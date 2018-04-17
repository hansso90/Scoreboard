package nl.teamrockstars.chapter.east.scoreboard.controller;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

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
import nl.teamrockstars.chapter.east.scoreboard.model.Activity;
import nl.teamrockstars.chapter.east.scoreboard.repository.ActivityRepository;

@RestController
@RequestMapping(value = PUBLIC + "/activity")
@PreAuthorize("hasRole('ACTIVITYMANAGEMENT')")
@Api(tags = "Activity Controller", description = "Management of activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get activity", notes = "Gets a certain activity with id", response = Activity.class)
    public ResponseEntity<Activity> getActivity(@PathVariable("id") Long id) {
        return new ResponseEntity<Activity>(activityRepository.findById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "Create activity", notes = "Create a new activity")
    public HttpStatus postActivity(@RequestBody Activity activity) {
      //TODO: check content;
      activityRepository.save(activity);
      return HttpStatus.ACCEPTED;
    }
}
