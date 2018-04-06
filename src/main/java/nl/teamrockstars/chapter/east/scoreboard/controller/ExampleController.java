package nl.teamrockstars.chapter.east.scoreboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.teamrockstars.chapter.east.scoreboard.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

@RestController
@RequestMapping(value = PUBLIC + "/example")
@Api(tags = "Public Example Controller", description = "Example Controller")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @RequestMapping(value = "secure", method = RequestMethod.GET)
    @ApiOperation(value = "Check public controller is accessible", notes = "", response = String.class)
    public String getSecure()
    {
        return exampleService.exampleMethod();
    }
}
