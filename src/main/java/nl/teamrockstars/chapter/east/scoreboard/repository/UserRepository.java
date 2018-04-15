package nl.teamrockstars.chapter.east.scoreboard.repository;


import nl.teamrockstars.chapter.east.scoreboard.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
