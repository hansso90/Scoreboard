package nl.teamrockstars.chapter.east.scoreboard.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity(name = "user")
public class User extends AbstractHibernateObject implements UserDetails {

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled = false; //default disabled

    @Column(name = "locked")
    private Boolean locked = true; // default locked

    @Column(name = "expired", nullable = true)
    private LocalDateTime expired = LocalDateTime.now().minusDays(1); //default expired

    @ManyToOne
    @JoinColumn(name = "chapter")
    private Chapter chapter;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getRights();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //!expired.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //!locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //!expired.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isEnabled() {
        return true; //enabled;
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
