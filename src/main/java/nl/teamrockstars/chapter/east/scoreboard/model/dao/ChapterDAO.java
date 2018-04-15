package nl.teamrockstars.chapter.east.scoreboard.model.dao;

import javax.transaction.Transactional;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface ChapterDAO extends CrudRepository<Chapter, Long> {

}
