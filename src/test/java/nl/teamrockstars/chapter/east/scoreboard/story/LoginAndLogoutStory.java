package nl.teamrockstars.chapter.east.scoreboard.story;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.MOUNT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import nl.teamrockstars.chapter.east.scoreboard.controller.AbstractControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginAndLogoutStory extends AbstractControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getLoginAndLogoutTest() throws Exception {
    String token = getToken("admin", "password");

    mvc.perform(get(MOUNT + "/user/current")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk());

    logout(token);

    mvc.perform(get(MOUNT + "/user/current")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isUnauthorized());
  }
}
