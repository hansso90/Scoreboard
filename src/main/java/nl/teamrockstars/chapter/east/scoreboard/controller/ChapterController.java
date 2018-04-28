package nl.teamrockstars.chapter.east.scoreboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.dto.CategoryDto;
import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.ChapterMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import nl.teamrockstars.chapter.east.scoreboard.repository.ChapterRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

@RestController
@RequestMapping(value = PUBLIC + "/chapter")
@PreAuthorize("hasRole('CHAPTERMANAGEMENT')")
@Api(tags = "Chapter Controller", description = "Management of categories")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ChapterRepository repository;

    @Autowired
    private ChapterMapper mapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get Chapter", notes = "Gets a certain Chapter with id", response = ChapterDto.class)
    public ResponseEntity<ChapterDto> single(@PathVariable("id") Long id) {

        Chapter Chapter = repository.findOne(id);

        if (Chapter == null) {

            return new ResponseEntity<ChapterDto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ChapterDto>(mapper.toDto(Chapter), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "Get categories", notes = "Gets all categories", response = ChapterDto.class)
    public ResponseEntity<List<ChapterDto>> list() {
        List<Chapter> categories = repository.findAllByOrderByName();
        return new ResponseEntity<List<ChapterDto>>(mapper.toDtoList(categories), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "Create Chapter", notes = "Create a new Chapter")
    public ResponseEntity<ChapterDto> create(@RequestBody @Valid ChapterDto chapter, BindingResult errors) throws MethodArgumentNotValidException {

        Map<String, String> map = chapterService.validate(chapter, false);
        map.forEach((index, text)-> errors.rejectValue(index, text));
        if (errors.hasErrors()) {
            throw new MethodArgumentNotValidException(null, errors);
        }
        ChapterDto dto = chapterService.submit(chapter);
        return new ResponseEntity<ChapterDto>(dto, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Chapter", notes = "Update a Chapter")
    public ResponseEntity<ChapterDto> update(@RequestBody @Valid ChapterDto chapter, BindingResult errors) throws MethodArgumentNotValidException {
        Map<String, String> map = chapterService.validate(chapter, true);
        map.forEach((index, text)-> errors.rejectValue(index, text));
        if (errors.hasErrors()) {
            throw new MethodArgumentNotValidException(null, errors);
        }
        ChapterDto dto = chapterService.submit(chapter);
        return new ResponseEntity<ChapterDto>(dto, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete Chapter", notes = "Deletes a certain Chapter with id")
    public HttpStatus delete(@PathVariable("id") Long id) {
        repository.delete(id);
        return HttpStatus.ACCEPTED;
    }

}
