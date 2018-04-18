package nl.teamrockstars.chapter.east.scoreboard.controller;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.ChapterMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import nl.teamrockstars.chapter.east.scoreboard.repository.ChapterRepository;

@RestController
@RequestMapping(value = PUBLIC + "/chapter")
@PreAuthorize("hasRole('ACTIVITYMANAGEMENT')")
@Api(tags = "Chapter Controller", description = "Management of categories")
public class ChapterController {

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
	public HttpStatus create(@RequestBody @Valid ChapterDto Chapter, BindingResult errors) throws MethodArgumentNotValidException {
		
		if(Chapter.getId() != null) {
			errors.rejectValue("id", "ID must be empty on POST");
			throw new MethodArgumentNotValidException(null, errors);
		}
		Chapter cat = mapper.fromDto(Chapter);

		repository.save(cat);
		return HttpStatus.ACCEPTED;
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ApiOperation(value = "Update Chapter", notes = "Update a Chapter")
	public HttpStatus update(@RequestBody @Valid ChapterDto Chapter, BindingResult errors) throws MethodArgumentNotValidException {
		
		Chapter cat = mapper.fromDto(Chapter);
		if(cat.isNew()) {
			errors.rejectValue("id", "ID could not be found");
			throw new MethodArgumentNotValidException(null, errors);
		}

		repository.save(cat);
		return HttpStatus.ACCEPTED;
	}
}
