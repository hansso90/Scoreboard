package nl.teamrockstars.chapter.east.scoreboard.model;


import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractHibernateObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractHibernateObject that = (AbstractHibernateObject) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
