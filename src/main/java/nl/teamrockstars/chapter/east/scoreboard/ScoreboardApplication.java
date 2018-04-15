package nl.teamrockstars.chapter.east.scoreboard;

import nl.teamrockstars.chapter.east.scoreboard.model.Right;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import nl.teamrockstars.chapter.east.scoreboard.service.RoleService;
import nl.teamrockstars.chapter.east.scoreboard.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "nl.teamrockstars.chapter.east.scoreboard")
public class ScoreboardApplication {

    private static Logger LOG = LoggerFactory.getLogger(ScoreboardApplication.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ScoreboardApplication.class, args);
    }

    @PostConstruct
    public void createFirstUser() {

        Role role = roleService.createNewRole("admin", Right.values());


        String password = "password";
        User user = userService.createNewUser("admin", "password", "Beheerder", role);

        LOG.info("##################################################");
        LOG.info("                 username: " + user.getUsername() + "                  ");
        LOG.info("                 password: " + password + "        ");
        LOG.info("##################################################");
    }
}
