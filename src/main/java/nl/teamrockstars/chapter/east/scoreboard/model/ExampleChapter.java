package nl.teamrockstars.chapter.east.scoreboard.model;

import javax.persistence.Entity;

@Entity(name = "chapter")
public class ExampleChapter extends AbstractHibernateObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
