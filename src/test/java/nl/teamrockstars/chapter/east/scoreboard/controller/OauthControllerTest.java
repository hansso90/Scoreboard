package nl.teamrockstars.chapter.east.scoreboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class OauthControllerTest extends AbstractControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getTokenTest() throws Exception {
    String token = getToken("admin", "password");
  }

  @Test
  public void getLogoutTest() throws Exception {
    String token = getToken("admin", "password");

    logout(token);
  }

  @Test
  public void getLogoutInvalidTokenTest() throws Exception {
    String token = "d955332c-f09b-471e-94e5-fe6cbf49960b";

    mvc.perform(delete("/oauth/logout")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isUnauthorized());
  }
}
