package nl.teamrockstars.chapter.east.scoreboard.service;

import nl.teamrockstars.chapter.east.scoreboard.dto.CategoryDto;
import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;

import java.util.Map;

public interface CategoryService {

    Map<String, String> validateAndSubmit(CategoryDto categoryDto, Boolean alreadyExist);
}
