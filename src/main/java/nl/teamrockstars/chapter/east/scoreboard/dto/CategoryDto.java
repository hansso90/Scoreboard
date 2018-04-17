package nl.teamrockstars.chapter.east.scoreboard.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
public class CategoryDto extends DtoObject {

	@NotBlank
	private String name;

	@Min(0)
	private Long defaultStardust;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDefaultStardust() {
		return defaultStardust;
	}

	public void setDefaultStardust(Long defaultStardust) {
		this.defaultStardust = defaultStardust;
	}
}
