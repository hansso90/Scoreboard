package nl.teamrockstars.chapter.east.scoreboard.service.impl;

import nl.teamrockstars.chapter.east.scoreboard.dto.ActivityDto;
import nl.teamrockstars.chapter.east.scoreboard.dto.CategoryDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.ActivityMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.Activity;
import nl.teamrockstars.chapter.east.scoreboard.model.Category;
import nl.teamrockstars.chapter.east.scoreboard.repository.ActivityRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sun.dc.pr.PRError;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityMapper mapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, String> validateAndSubmit(ActivityDto activityDto, Boolean alreadyExist) {
        HashMap<String, String> map = new HashMap();
        if (activityDto == null) {
            map.put("Chapter", "There is no chapter information.");
        }
        if (alreadyExist && activityDto.getId() == null) {
            map.put("Chapter", "There is no chapter information.");
        }
        if (alreadyExist && activityDto.getId() != null && activityRepository.findOne(activityDto.getId()) == null) {
            map.put("Chapter", "There is no chapter information with id " + activityDto.getId());
        }
        if (!alreadyExist && activityDto.getId() != null) {
            map.put("Chapter", "There could not be a id if the chapter is new ");
        }

        if (CollectionUtils.isEmpty(map)) {
            Activity activity = mapper.fromDto(activityDto);
            activityRepository.save(activity);
        }
        return map;
    }
}
