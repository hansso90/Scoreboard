package nl.teamrockstars.chapter.east.scoreboard.repository;

import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findById(Long id);
}
