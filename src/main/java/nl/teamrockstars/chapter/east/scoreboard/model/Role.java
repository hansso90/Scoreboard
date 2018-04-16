package nl.teamrockstars.chapter.east.scoreboard.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "role")
public class Role extends AbstractHibernateObject {

    @Column(name = "name")
    private String name;

    @Column(name = "rights")
    @ElementCollection(targetClass = Right.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Right> rights;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
