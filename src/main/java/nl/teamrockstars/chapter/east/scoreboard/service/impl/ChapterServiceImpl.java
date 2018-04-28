package nl.teamrockstars.chapter.east.scoreboard.service.impl;

import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.ChapterMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import nl.teamrockstars.chapter.east.scoreboard.repository.ChapterRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ChapterMapper mapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Chapter createNewChapter(String name) {
        Chapter chapter = new Chapter();
        chapter.setName(name);

        chapterRepository.save(chapter);

        return chapter;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ChapterDto submit(ChapterDto chapterDto) {
        Chapter chapter = chapterRepository.save(mapper.fromDto(chapterDto));
        return mapper.toDto(chapter);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, String> validate(ChapterDto chapterDto, Boolean alreadyExist) {
        HashMap<String, String> map = new HashMap();
        if (chapterDto == null) {
            map.put("Chapter", "There is no chapter information.");
        }
        if (alreadyExist && chapterDto.getId() == null) {
            map.put("Chapter", "There is no chapter information.");
        }
        if (alreadyExist && chapterDto.getId() != null && chapterRepository.findOne(chapterDto.getId()) == null) {
            map.put("Chapter", "There is no chapter information with id " + chapterDto.getId());
        }
        if (!alreadyExist && chapterDto.getId() != null) {
            map.put("Chapter", "There could not be a id if the chapter is new ");
        }
        return map;
    }
}
