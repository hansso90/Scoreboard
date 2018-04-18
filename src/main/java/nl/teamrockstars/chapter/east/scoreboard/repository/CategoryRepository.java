package nl.teamrockstars.chapter.east.scoreboard.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import nl.teamrockstars.chapter.east.scoreboard.model.Category;


public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findAllByOrderByName();
	
}
