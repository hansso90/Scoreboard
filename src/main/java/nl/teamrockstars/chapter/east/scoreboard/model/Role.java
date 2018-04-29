package nl.teamrockstars.chapter.east.scoreboard.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

@Entity(name = "role")
public class Role extends AbstractHibernateObject {
	
	public class Names {
		
		public static final String ADMIN = "admin";
		public static final String USER = "user";
	}

  @Column(name = "name", unique = true, nullable = false)
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
