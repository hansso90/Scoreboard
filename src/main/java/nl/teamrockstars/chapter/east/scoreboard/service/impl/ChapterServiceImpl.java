package nl.teamrockstars.chapter.east.scoreboard.service.impl;

import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import nl.teamrockstars.chapter.east.scoreboard.repository.ChapterRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ChapterServiceImpl implements ChapterService {

  @Autowired
  private ChapterRepository chapterRepository;

  @Override
  public Chapter createNewChapter(String name) {
    Chapter chapter = new Chapter();
    chapter.setName(name);

    chapterRepository.save(chapter);

    return chapter;
  }
}
