package nl.teamrockstars.chapter.east.scoreboard.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;

public interface ChapterRepository extends CrudRepository<Chapter, Long> {

    List<Chapter> findAllByOrderByName();

}
