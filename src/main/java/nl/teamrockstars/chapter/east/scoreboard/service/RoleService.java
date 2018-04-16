package nl.teamrockstars.chapter.east.scoreboard.service;

import nl.teamrockstars.chapter.east.scoreboard.model.Right;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;

public interface RoleService {

    Role findById(Long id);

    Role createNewRole(String name, Right... rights);
}
