package nl.teamrockstars.chapter.east.scoreboard.service.impl;

import nl.teamrockstars.chapter.east.scoreboard.service.ExampleService;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Override
    public String exampleMethod() {
        return "example";
    }
}


