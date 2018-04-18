package nl.teamrockstars.chapter.east.scoreboard.dto;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;

public class ActivityDto extends DtoObject {

    private String description;

    private ZonedDateTime date;

    @Column(name = "starDust")
    private Long stardust;

    private DtoObject category;

    private List<BaseUserDto> users;

    private DtoObject chapter;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStardust() {
        return stardust;
    }

    public void setStardust(Long stardust) {
        this.stardust = stardust;
    }

    public DtoObject getCategory() {
        return category;
    }

    public void setCategory(DtoObject category) {
        this.category = category;
    }

    public ZonedDateTime getDate() {
			return date;
		}

		public void setDate(ZonedDateTime date) {
			this.date = date;
		}

		public List<BaseUserDto> getUsers() {
			return users;
		}

		public void setUsers(List<BaseUserDto> users) {
			this.users = users;
		}

		public DtoObject getChapter() {
        return chapter;
    }

    public void setChapter(DtoObject chapter) {
        this.chapter = chapter;
    }
}
