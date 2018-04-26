package nl.teamrockstars.chapter.east.scoreboard.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

public abstract class AbstractControllerTest {

  @Autowired
  private MockMvc mvc;

  protected String getToken(String username, String password) throws Exception {
    String content = "grant_type=password&username=" + username + "&password=" + password;

    MvcResult result = mvc.perform(post("/oauth/token")
        .with(httpBasic("scoreboard", "123456"))
        .content(content)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .accept("application/json;charset=UTF-8"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8")).andReturn();

    String responseContent = result.getResponse().getContentAsString();
    JacksonJsonParser jsonParser = new JacksonJsonParser();
    String token = jsonParser.parseMap(responseContent).get("access_token").toString();
    assertNotNull(token, "Did not return any token, token is needed for the test");
    return token;
  }


  protected void logout(String token) throws Exception {
    mvc.perform(delete("/oauth/logout")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk());
  }
}
