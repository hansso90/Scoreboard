package nl.teamrockstars.chapter.east.scoreboard.repository;

import nl.teamrockstars.chapter.east.scoreboard.model.Activity;
import org.springframework.data.repository.CrudRepository;


public interface ActivityRepository extends CrudRepository<Activity, Long> {

    Activity findById(Long id);
}
