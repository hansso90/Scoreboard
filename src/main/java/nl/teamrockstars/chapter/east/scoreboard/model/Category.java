package nl.teamrockstars.chapter.east.scoreboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "category")
public class Category extends AbstractHibernateObject {

  @Column(name = "name")
  private String name;

  // Default value for amount of stardust for activities in this category
  @Column(name = "defaultStardust")
  private Long defaultStardust;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getDefaultStardust() {
    return defaultStardust;
  }

  public void setDefaultStardust(Long defaultStardust) {
    this.defaultStardust = defaultStardust;
  }

  @Override
  public String toString() {
    return "Category{" +
        "name='" + name + '\'' +
        ", defaultStardust=" + defaultStardust +
        '}';
  }
}
