package com.spnikit.ylabcourse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spnikit.ylabcourse.controller.TicTacToeGameController;
import com.spnikit.ylabcourse.request.model.Move;
import com.spnikit.ylabcourse.request.model.PlayerRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class YlabCourseApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicTacToeGameController controller;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(controller);
    }



    @Test
    public void shouldReturnValidPlayerJsonWhenRegistered() throws Exception {

        this.mockMvc.perform(
                        post("/gameplay/register/player")
                                .content(asJsonString(new PlayerRequest("Serge", "X")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._id").value(1))
                .andExpect(jsonPath("$._name").value("Serge"))
                .andExpect(jsonPath("$._symbol").value("X"));

    }


    @Test
    public void shouldReturnValidMoveResponseObject() throws Exception {

        this.mockMvc.perform(
                        post("/gameplay/move")
                                .content(asJsonString(new Move(1, 1)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.board").isArray())
                .andExpect(jsonPath("$.playerNextMoveId").value(2))
                .andExpect(jsonPath("$.winnerId").isEmpty())
                .andExpect(jsonPath("$.draw").value(false));
    }


    @Test
    public void shouldReturnBadRequestWithMessage() throws Exception {
        this.mockMvc.perform(
                        post("/gameplay/move")
                                .content(asJsonString(new Move(10, 1)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.localDate").exists());

    }




    private static String asJsonString(Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Unable to serialize object " + obj + " to JSON string");
            throw new RuntimeException(e);
        }
    }

}
