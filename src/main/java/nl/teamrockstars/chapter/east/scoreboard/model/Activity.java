package nl.teamrockstars.chapter.east.scoreboard.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "activity")
public class Activity extends AbstractHibernateObject {

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "starDust")
    private Long stardust;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @ManyToMany
    @JoinColumn(name = "user")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "chapter")
    private Chapter chapter;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getStardust() {
        return stardust;
    }

    public void setStardust(Long stardust) {
        this.stardust = stardust;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<User> getUser() {
        return users;
    }

    public void setUser(List<User> user) {
        users = user;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "description='" + description + '\'' +
                ", date=" + date +
                ", stardust=" + stardust +
                ", category=" + category +
                ", users=" + users +
                ", chapter=" + chapter +
                '}';
    }
}
