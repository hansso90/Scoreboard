package nl.teamrockstars.chapter.east.scoreboard.service;

import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;

import java.util.Map;

public interface ChapterService {

    Chapter createNewChapter(String name);

    Map<String, String> validate(ChapterDto chapterDto, Boolean alreadyExist);

    ChapterDto submit(ChapterDto chapterDto);

}
