package nl.teamrockstars.chapter.east.scoreboard.controller;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get category", notes = "Gets a certain category with id", response = CategoryDto.class)
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) {

		Category category = categoryRepository.findOne(id);
		
		if (category == null) {
			
			return new ResponseEntity<CategoryDto>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CategoryDto>(CategoryMapper.INSTANCE.categoryToCategoryDto(category), HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiOperation(value = "Create category", notes = "Create a new category")
	public HttpStatus postCategory(@RequestBody @Valid CategoryDto category) {
		
		// TODO: check content;
		Category cat = CategoryMapper.INSTANCE.categoryDtoToCategory(category);

		categoryRepository.save(cat);
		return HttpStatus.ACCEPTED;
	}
}
