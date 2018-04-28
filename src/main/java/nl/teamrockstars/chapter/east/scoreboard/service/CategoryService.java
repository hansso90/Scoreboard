package nl.teamrockstars.chapter.east.scoreboard.service;

import nl.teamrockstars.chapter.east.scoreboard.dto.CategoryDto;
import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;

import java.util.Map;

public interface CategoryService {

    CategoryDto submit(CategoryDto categoryDto);

    Map<String, String> validate(CategoryDto categoryDto, Boolean alreadyExist);
}
