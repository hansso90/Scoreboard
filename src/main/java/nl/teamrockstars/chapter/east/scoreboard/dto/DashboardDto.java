package nl.teamrockstars.chapter.east.scoreboard.dto;

import java.util.List;
public class DashboardDto {

	private List<DashboardChapterDto> chapters;

	public List<DashboardChapterDto> getChapters() {
		return chapters;
	}

	public void setChapters(List<DashboardChapterDto> chapters) {
		this.chapters = chapters;
	}
	
}
