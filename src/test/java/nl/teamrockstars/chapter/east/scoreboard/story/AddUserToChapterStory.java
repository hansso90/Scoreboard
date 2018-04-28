package nl.teamrockstars.chapter.east.scoreboard.story;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.teamrockstars.chapter.east.scoreboard.controller.AbstractControllerTest;
import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;
import nl.teamrockstars.chapter.east.scoreboard.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.MOUNT;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddUserToChapterStory extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void addUserToChapterStory() throws Exception {
        String chapterName = "Chapter voorbeeld east";
        String userName = "John Doe";

        ObjectMapper mapper = new ObjectMapper();
        String token = getToken("admin", "password");

        ChapterDto chapterDto = new ChapterDto();
        chapterDto.setName(chapterName);

        // save chapterdto
        MvcResult result = mvc.perform(post(MOUNT + "/chapter")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(chapterDto)))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType("application/json;charset=UTF-8")).andReturn();

        String responseContent = result.getResponse().getContentAsString();
        chapterDto = mapper.readValue(responseContent, ChapterDto.class);

        UserDto dto = new UserDto();
        dto.setName("John Doe");
        dto.setChapter(chapterDto);


        result = mvc.perform(post(MOUNT + "/user")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType("application/json;charset=UTF-8")).andReturn();

        responseContent = result.getResponse().getContentAsString();
        dto = mapper.readValue(responseContent, UserDto.class);

        assertEquals("Chaptername needs to be the name "+chapterName+"!", chapterName, dto.getChapter().getName());
        assertEquals("Username needs to be the name "+userName+"!", chapterName, dto.getChapter().getName());
    }
}
