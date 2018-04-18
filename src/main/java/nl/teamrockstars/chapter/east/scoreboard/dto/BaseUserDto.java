package nl.teamrockstars.chapter.east.scoreboard.dto;

public class BaseUserDto extends CategoryDto {

  private String name;

  private String chapter;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getChapter() {
    return chapter;
  }

  public void setChapter(String chapter) {
    this.chapter = chapter;
  }
}
