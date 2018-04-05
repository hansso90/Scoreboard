package nl.teamrockstars.chapter.east.scoreboard.controller;

import nl.teamrockstars.chapter.east.scoreboard.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping
    public String getExample()
    {
        return exampleService.exampleMethod();
    }
}
