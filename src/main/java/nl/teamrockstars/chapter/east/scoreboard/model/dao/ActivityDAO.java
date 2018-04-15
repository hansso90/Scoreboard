package nl.teamrockstars.chapter.east.scoreboard.model.dao;

import javax.transaction.Transactional;
import nl.teamrockstars.chapter.east.scoreboard.model.Activity;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface ActivityDAO extends CrudRepository<Activity, Long> {

}
