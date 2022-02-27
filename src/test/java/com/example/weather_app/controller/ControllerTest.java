package com.example.weather_app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    private static final String URL = "/forecast";
    private static final String DATE = "?date=2022-03-01";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getSurfingSpot() throws Exception {
        //given

        //when
        mockMvc.perform(get(URL + DATE))
                .andDo(print())
                //then
                .andExpect(status().isOk());
    }
}