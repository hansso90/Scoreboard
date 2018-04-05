package nl.teamrockstars.chapter.east.scoreboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "nl.teamrockstars.chapter.east.scoreboard")
public class ScoreboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoreboardApplication.class, args);
	}
}
