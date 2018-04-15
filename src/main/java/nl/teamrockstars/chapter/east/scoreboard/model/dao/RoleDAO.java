package nl.teamrockstars.chapter.east.scoreboard.model.dao;

import javax.transaction.Transactional;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface RoleDAO extends CrudRepository<Role, Long> {

}
