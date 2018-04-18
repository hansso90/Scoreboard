package nl.teamrockstars.chapter.east.scoreboard.dto;

import java.util.List;

public class UserDto extends BaseUserDto {

	private String role;

	private List<String> rights;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<String> getRights() {
		return rights;
	}

	public void setRights(List<String> rights) {
		this.rights = rights;
	}
}
