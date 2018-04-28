package nl.teamrockstars.chapter.east.scoreboard.service.impl;

import nl.teamrockstars.chapter.east.scoreboard.dto.CategoryDto;
import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.CategoryMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.Category;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import nl.teamrockstars.chapter.east.scoreboard.repository.CategoryRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper mapper;

    @Override
    public CategoryDto submit(CategoryDto categoryDto) {
        Category category = categoryRepository.save(mapper.fromDto(categoryDto));
        return mapper.toDto(category);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, String> validate(CategoryDto categoryDto, Boolean alreadyExist) {
        HashMap<String, String> map = new HashMap();
        if (categoryDto == null) {
            map.put("Chapter", "There is no chapter information.");
        }
        if (alreadyExist && categoryDto.getId() == null) {
            map.put("Chapter", "There is no chapter information.");
        }
        if (alreadyExist && categoryDto.getId() != null && categoryRepository.findOne(categoryDto.getId()) == null) {
            map.put("Chapter", "There is no chapter information with id " + categoryDto.getId());
        }
        if (!alreadyExist && categoryDto.getId() != null) {
            map.put("Chapter", "There could not be a id if the chapter is new ");
        }
        return map;
    }
}
