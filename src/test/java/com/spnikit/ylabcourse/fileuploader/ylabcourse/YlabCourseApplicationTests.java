package com.spnikit.ylabcourse.fileuploader.ylabcourse;

import com.spnikit.ylabcourse.fileuploader.ylabcourse.controller.TicTacToeGameController;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model.Move;
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
        var moveTest = new Move();
        moveTest.setCellNumber(1);
        moveTest.setPlayerId(1);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/gameplay/move")
                        .content("{ \"cellNumber\" : 1, \"playerId\": 1 }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.board").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerNextMoveId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winnerId").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.draw").value(false));
    }

}
