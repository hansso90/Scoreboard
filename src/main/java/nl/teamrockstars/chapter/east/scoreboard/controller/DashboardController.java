package nl.teamrockstars.chapter.east.scoreboard.controller;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.dto.DashboardChapterDto;
import nl.teamrockstars.chapter.east.scoreboard.dto.DashboardDto;

@RestController
@RequestMapping(value = PUBLIC + "/dashboard")
@PreAuthorize("hasRole('DASHBOARD')")
@Api(tags = "Dashboard Controller", description = "Viewing of the main dashboard")
public class DashboardController {


	@RequestMapping(value = "", method = RequestMethod.GET)
  @ApiOperation(value = "Get dashboard information", notes = "Gets basic dashboard information", response = DashboardDto.class)
  public ResponseEntity<DashboardDto> dashboardData() {
  	
		DashboardDto dto = new DashboardDto();

		Map<String,String> colors;

			colors = new HashMap<String,String>();
				colors.put("West","orange");
				colors.put("East","pink");
				colors.put("North","blue");
				colors.put("South","green");

		List<String> names = Lists.newArrayList("West", "East","North","South");
		List<DashboardChapterDto> chapters = new ArrayList<>();
		
		for(String name : names) {
			
			DashboardChapterDto chapter = new DashboardChapterDto();
			chapter.setChapterColor(colors.get(name));
			chapter.setChapterName(name);
			chapter.setMemberCount(random(20, 35));
			chapter.setStardust(random(80, 150));
			
			chapters.add(chapter);
		}
		
		dto.setChapters(chapters);

		return new ResponseEntity<DashboardDto>(dto, HttpStatus.OK);
	}
	
	private static final Long random(int from, int to) {
		
		int diff = to - from;
		Random rn = new Random();
		int i = rn.nextInt(diff + 1);
		return new Long(i + from);
	}
}
