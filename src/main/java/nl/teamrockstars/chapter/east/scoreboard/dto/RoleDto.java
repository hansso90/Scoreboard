package nl.teamrockstars.chapter.east.scoreboard.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

public class RoleDto extends DtoObject {

    @NotBlank
    private String name;

    private List<String> rights;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRights() {
        return rights;
    }

    public void setRights(List<String> rights) {
        this.rights = rights;
    }
}
