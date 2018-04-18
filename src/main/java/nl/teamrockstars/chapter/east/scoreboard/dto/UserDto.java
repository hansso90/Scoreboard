package nl.teamrockstars.chapter.east.scoreboard.dto;

import java.time.ZonedDateTime;
import java.util.List;

public class UserDto extends BaseUserDto {

	private String role;

	private List<String> rights;
	
	private ZonedDateTime lastModifiedAt;
	
	private ZonedDateTime createdAt;

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

	public ZonedDateTime getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(ZonedDateTime lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
