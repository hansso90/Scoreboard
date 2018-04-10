package nl.teamrockstars.chapter.east.scoreboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "chapter")
public class Chapter extends AbstractHibernateObject {

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
    return "Chapter{" +
        "name='" + name + '\'' +
        '}';
  }
}
