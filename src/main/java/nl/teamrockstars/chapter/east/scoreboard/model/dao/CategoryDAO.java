package nl.teamrockstars.chapter.east.scoreboard.model.dao;

import javax.transaction.Transactional;
import nl.teamrockstars.chapter.east.scoreboard.model.Category;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface CategoryDAO extends CrudRepository<Category, Long> {

}
