package nl.teamrockstars.chapter.east.scoreboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "user")
public class User extends AbstractHibernateObject {

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "chapter")
  private Chapter chapter;

  @ManyToOne
  @JoinColumn(name = "role")
  private Role role;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Chapter getChapter() {
    return chapter;
  }

  public void setChapter(Chapter chapter) {
    this.chapter = chapter;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", chapter=" + chapter +
        ", role=" + role +
        '}';
  }
}
