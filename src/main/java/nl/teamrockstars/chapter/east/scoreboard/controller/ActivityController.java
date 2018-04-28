package nl.teamrockstars.chapter.east.scoreboard.controller;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import nl.teamrockstars.chapter.east.scoreboard.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.dto.ActivityDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.ActivityMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.Activity;
import nl.teamrockstars.chapter.east.scoreboard.repository.ActivityRepository;

@RestController
@RequestMapping(value = PUBLIC + "/activity")
@PreAuthorize("hasRole('ACTIVITYMANAGEMENT')")
@Api(tags = "Activity Controller", description = "Management of activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

    @Autowired
    private ActivityRepository repository;
    
    @Autowired
  	private ActivityMapper mapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get activity", notes = "Gets a certain activity by id", response = ActivityDto.class)
    public ResponseEntity<ActivityDto> single(@PathVariable("id") Long id) {
    	
  		Activity activity = repository.findOne(id);
  		
  		if (activity == null) {
  			
  			return new ResponseEntity<ActivityDto>(HttpStatus.NOT_FOUND);
  		}

  		return new ResponseEntity<ActivityDto>(mapper.toDto(activity), HttpStatus.OK);
  	}
  	
  	@RequestMapping(value = "", method = RequestMethod.GET)
  	@ApiOperation(value = "Get activities", notes = "Gets all activities", response = ActivityDto.class)
  	public ResponseEntity<List<ActivityDto>> list() {

  		List<Activity> activities = repository.findAllByOrderByDateDesc();
  		return new ResponseEntity<List<ActivityDto>>(mapper.toDtoList(activities), HttpStatus.OK);
  	}
    
  	@RequestMapping(value = "", method = RequestMethod.POST)
  	@ApiOperation(value = "Create activity", notes = "Create a new activity")
  	public HttpStatus create(@RequestBody @Valid ActivityDto activity, BindingResult errors) throws MethodArgumentNotValidException {

		Map<String, String> map = activityService.validateAndSubmit(activity, false);
		map.forEach((index, text)-> errors.rejectValue(index, text));
		if(errors.hasErrors())
		{
			throw new MethodArgumentNotValidException(null, errors);
		}
  		return HttpStatus.ACCEPTED;
  	}
  	
  	@RequestMapping(value = "", method = RequestMethod.PUT)
  	@ApiOperation(value = "Update activity", notes = "Update an activity")
  	public HttpStatus update(@RequestBody @Valid ActivityDto activity, BindingResult errors) throws MethodArgumentNotValidException {

		Map<String, String> map = activityService.validateAndSubmit(activity, true);
		map.forEach((index, text)-> errors.rejectValue(index, text));
		if(errors.hasErrors())
		{
			throw new MethodArgumentNotValidException(null, errors);
		}
  		return HttpStatus.ACCEPTED;
  	}
  	
  	 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
     @ApiOperation(value = "Delete activity", notes = "Delete a certain activity by id")
     public HttpStatus delete(@PathVariable("id") Long id) {
     	
   		Activity activity = repository.findOne(id);
   		if (activity == null) {
   			return HttpStatus.NOT_FOUND;
   		}
   		repository.delete(activity);

   		return HttpStatus.OK;
   	}
}
