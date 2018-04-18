package nl.teamrockstars.chapter.east.scoreboard.repository;


import nl.teamrockstars.chapter.east.scoreboard.model.Activity;
import nl.teamrockstars.chapter.east.scoreboard.model.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findById(Long id);

		List<User> findAllByOrderByName();
}
