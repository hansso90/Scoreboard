package nl.teamrockstars.chapter.east.scoreboard.service;

import nl.teamrockstars.chapter.east.scoreboard.dto.ActivityDto;
import nl.teamrockstars.chapter.east.scoreboard.dto.CategoryDto;
import nl.teamrockstars.chapter.east.scoreboard.model.Activity;

import java.util.Map;

public interface ActivityService {

    Map<String, String> validateAndSubmit(ActivityDto activityDto, Boolean alreadyExist);
}
