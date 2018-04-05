package nl.teamrockstars.chapter.east.scoreboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "activity")
public class ExampleActivity extends AbstractHibernateObject{

    @Column(name = "name")
    private String name;

    @Column(name = "stardust")
    private Long stardust;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStardust() {
        return stardust;
    }

    public void setStardust(Long stardust) {
        this.stardust = stardust;
    }
}
