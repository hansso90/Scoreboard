package nl.teamrockstars.chapter.east.scoreboard.dto;

import java.time.ZonedDateTime;

public class UserDto extends BaseUserDto {
	
	private ZonedDateTime lastModifiedAt;
	
	private ZonedDateTime createdAt;
	
	private RoleDto role;

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

    public RoleDto getRole() {
        return role;
    }

    public void setRole( RoleDto role ) {
        this.role = role;
    }
}
