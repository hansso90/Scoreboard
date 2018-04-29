package nl.teamrockstars.chapter.east.scoreboard;

import javax.annotation.PostConstruct;
import nl.teamrockstars.chapter.east.scoreboard.model.Chapter;
import nl.teamrockstars.chapter.east.scoreboard.model.Right;
import nl.teamrockstars.chapter.east.scoreboard.model.Role;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import nl.teamrockstars.chapter.east.scoreboard.service.ChapterService;
import nl.teamrockstars.chapter.east.scoreboard.service.RoleService;
import nl.teamrockstars.chapter.east.scoreboard.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "nl.teamrockstars.chapter.east.scoreboard")
public class ScoreboardApplication extends RepositoryRestConfigurerAdapter {

  private static Logger LOG = LoggerFactory.getLogger(ScoreboardApplication.class);

  @Autowired
  private RoleService roleService;

  @Autowired
  private UserService userService;

  @Autowired
  private ChapterService chapterService;

  public static void main(String[] args) {
    SpringApplication.run(ScoreboardApplication.class, args);
  }

  @PostConstruct
  public void createFirstUser() {

    Chapter chapter = chapterService.createNewChapter("MT");
    Role role = roleService.createNewRole(Role.Names.ADMIN, Right.values());
    
    roleService.createNewRole(Role.Names.USER, Right.ROLE_DASHBOARD);

    String password = "password";
    User user = userService.createNewUser("admin", "password", "Beheerder", role, chapter);

    LOG.info("##################################################");
    LOG.info("                 username: " + user.getUsername() + "                  ");
    LOG.info("                 password: " + password + "        ");
    LOG.info("##################################################");
  }
}
