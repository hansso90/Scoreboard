package nl.teamrockstars.chapter.east.scoreboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "role")
public class Role extends AbstractHibernateObject {

  @Column(name = "name")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Role{" +
        "name='" + name + '\'' +
        '}';
  }
}
