package nl.teamrockstars.chapter.east.scoreboard.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import nl.teamrockstars.chapter.east.scoreboard.model.Activity;


public interface ActivityRepository extends CrudRepository<Activity, Long> {

    List<Activity> findAllByOrderByDateDesc();
    
}
