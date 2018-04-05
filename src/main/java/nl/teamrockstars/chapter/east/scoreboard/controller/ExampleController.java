package nl.teamrockstars.chapter.east.scoreboard.controller;

import nl.teamrockstars.chapter.east.scoreboard.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.BASE;
import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.MOUNT;
import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PUBLIC;

@RestController(BASE+MOUNT+PUBLIC)
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping(name = "example")
    public String getExample()
    {
        return exampleService.exampleMethod();
    }
}
