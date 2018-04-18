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
import nl.teamrockstars.chapter.east.scoreboard.dto.CategoryDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.CategoryMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.Category;
import nl.teamrockstars.chapter.east.scoreboard.repository.CategoryRepository;

@RestController
@RequestMapping(value = PUBLIC + "/category")
@PreAuthorize("hasRole('ACTIVITYMANAGEMENT')")
@Api(tags = "Category Controller", description = "Management of categories")
public class CategoryController {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private CategoryMapper mapper;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get category", notes = "Gets a certain category with id", response = CategoryDto.class)
	public ResponseEntity<CategoryDto> single(@PathVariable("id") Long id) {

		Category category = repository.findOne(id);
		
		if (category == null) {
			
			return new ResponseEntity<CategoryDto>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CategoryDto>(mapper.toDto(category), HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "Get categories", notes = "Gets all categories", response = CategoryDto.class)
	public ResponseEntity<List<CategoryDto>> list() {

		List<Category> categories = repository.findAllByOrderByName();
		return new ResponseEntity<List<CategoryDto>>(mapper.toDtoList(categories), HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiOperation(value = "Create category", notes = "Create a new category")
	public HttpStatus create(@RequestBody @Valid CategoryDto category, BindingResult errors) throws MethodArgumentNotValidException {
		
		if(category.getId() != null) {
			errors.rejectValue("id", "ID must be empty on POST");
			throw new MethodArgumentNotValidException(null, errors);
		}
		Category cat = mapper.fromDto(category);

		repository.save(cat);
		return HttpStatus.ACCEPTED;
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ApiOperation(value = "Update category", notes = "Update a category")
	public HttpStatus update(@RequestBody @Valid CategoryDto category, BindingResult errors) throws MethodArgumentNotValidException {
		
		Category cat = mapper.fromDto(category);
		if(cat.isNew()) {
			errors.rejectValue("id", "ID could not be found");
			throw new MethodArgumentNotValidException(null, errors);
		}

		repository.save(cat);
		return HttpStatus.ACCEPTED;
	}
}