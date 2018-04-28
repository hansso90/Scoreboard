package nl.teamrockstars.chapter.east.scoreboard.dto;

import java.time.ZonedDateTime;
import java.util.List;

public class UserDto extends BaseUserDto {
	
	private ZonedDateTime lastModifiedAt;
	
	private ZonedDateTime createdAt;

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
