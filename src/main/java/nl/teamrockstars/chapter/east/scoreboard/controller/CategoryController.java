package nl.teamrockstars.chapter.east.scoreboard.controller;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;
import nl.teamrockstars.chapter.east.scoreboard.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
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
	private CategoryService categoryService;

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
	public ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryDto category, BindingResult errors) throws MethodArgumentNotValidException {

		Map<String, String> map = categoryService.validate(category, false);
		map.forEach((index, text)-> errors.rejectValue(index, text));
		if(errors.hasErrors())
		{
			throw new MethodArgumentNotValidException(null, errors);
		}
        CategoryDto dto = categoryService.submit(category);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ApiOperation(value = "Update category", notes = "Update a category")
	public ResponseEntity<CategoryDto> update(@RequestBody @Valid CategoryDto category, BindingResult errors) throws MethodArgumentNotValidException {

		Map<String, String> map = categoryService.validate(category, true);
		map.forEach((index, text)-> errors.rejectValue(index, text));
		if(errors.hasErrors())
		{
			throw new MethodArgumentNotValidException(null, errors);
		}
        CategoryDto dto = categoryService.submit(category);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete category", notes = "Delete a category")
	public HttpStatus delete(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
		repository.delete(id);
		return HttpStatus.ACCEPTED;
	}
}
