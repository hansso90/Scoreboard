package nl.teamrockstars.chapter.east.scoreboard.dto;

public class BaseUserDto extends DtoObject {

    private String name;

    private ChapterDto chapter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChapterDto getChapter() {
        return chapter;
    }

    public void setChapter(ChapterDto chapter) {
        this.chapter = chapter;
    }
}
