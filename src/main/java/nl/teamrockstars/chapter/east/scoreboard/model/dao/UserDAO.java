package nl.teamrockstars.chapter.east.scoreboard.model.dao;

import javax.transaction.Transactional;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface UserDAO extends CrudRepository<User, Long> {

}
