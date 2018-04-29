package nl.teamrockstars.chapter.east.scoreboard.dto;

public class DashboardChapterDto {

	private String chapterName;

	private Long stardust;
	
	private Long memberCount;

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Long getStardust() {
		return stardust;
	}

	public void setStardust(Long stardust) {
		this.stardust = stardust;
	}

	public Long getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Long memberCount) {
		this.memberCount = memberCount;
	}
}
