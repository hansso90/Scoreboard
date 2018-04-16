package nl.teamrockstars.chapter.east.scoreboard.service.impl;

import nl.teamrockstars.chapter.east.scoreboard.model.Right;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.repository.RoleRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Role createNewRole(String name, Right... rights) {
        Role role = new Role();
        role.setRights(Arrays.asList(rights));
        role.setName(name);
        roleRepository.save(role);
        return role;
    }

}
