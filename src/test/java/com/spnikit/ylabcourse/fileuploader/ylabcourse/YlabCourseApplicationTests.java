package com.spnikit.ylabcourse.fileuploader.ylabcourse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.controller.TicTacToeGameController;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model.Move;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    public void shouldReturnValidMoveResponseObject() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/gameplay/move")
                        .content(asJsonString(new Move(1, 1)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.board").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerNextMoveId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winnerId").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.draw").value(false));
    }


    private static String asJsonString(Object obj){

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Unable to serialize object " + obj + " to JSON string");
            throw new RuntimeException(e);
        }
    }

}
