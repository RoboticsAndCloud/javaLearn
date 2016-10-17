/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.mvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;


import javax.servlet.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonefeifei.custom.security.WebSecurityConfig;
import com.lonefeifei.mvc.dto.GameDto;
import com.lonefeifei.mvc.rest.game.GameController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.lonefeifei.GameApplication;

import com.lonefeifei.domain.entity.Game;
import com.lonefeifei.domain.repository.GameRepository;

/**
 * Created by baidu on 16/8/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = {GameApplication.class})
@WebAppConfiguration
public class GameControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private GameRepository gameRepository;

    public static ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        gameRepository.deleteAll();
        for (int i = 0; i < 5; i++) {
            Game cfg = new Game();
            cfg.setName("name" + i);
            cfg.setComment("comment");
            cfg.setTitle("test");
            cfg.setContent("{}");
            cfg.setMaxParticipantCount(4);
            cfg.setParticipantCount(3);
            cfg.setMoney(10);
            cfg.setBetType("win");

            gameRepository.save(cfg);
        }
    }

    @Test
    public void testCreateGame() throws Exception {
        GameDto gameDto = new GameDto();

        gameDto.setName("nameDemo");
        gameDto.setComment("comment");
        gameDto.setTitle("test");
        gameDto.setContent("{}");
        gameDto.setMaxParticipantCount(4);
        gameDto.setParticipantCount(3);
        gameDto.setMoney(10);
        gameDto.setBetType("win");

        mvc.perform(post("/games").accept(MediaType.APPLICATION_JSON_UTF8)
                            .with(user("admin").password("admin"))
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(gameDto))
        )
                .andDo(print())
                .andExpect(jsonPath("$.name", is("nameDemo")));
    }


    @Test
    public void testFindGames() throws Exception {
        mvc.perform(get("/games").with(user("admin").password("admin"))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name0")));
        //result.getResponse().getContentAsString();
//                .andExpect(jsonPath("$.data.id", notNullValue()))
//                .andExpect(jsonPath("$.data.name", is("name1")))
//                .andExpect(jsonPath("$.data.comment", is("comment")))
//                .andExpect(jsonPath("$.data.title", is("saasTrend")));
    }

    /*
    @Test
    public void testDeleteWidget() throws Exception {
        mvc.perform(delete("/products/{productName}/widgets/{widgetName}", "productName", "name0")
                            .with(user("user").password("123456"))
                            .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andDo(print());
    }


    @Test
    public void testFindWidget() throws Exception {
        mvc.perform(get("/products/{productName}/widgets/{widgetName}", "productName", "name1")
                            .with(user("user").password("123456")).accept(MediaType.APPLICATION_JSON_UTF8)
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("name1")))
                .andExpect(jsonPath("$.data.comment", is("comment")))
                .andExpect(jsonPath("$.data.title", is("saasTrend")));
    }

    @Test
    public void testUpdateWidget() throws Exception {
        ConfigureDto cfg = new ConfigureDto();
        cfg.setProductName("product");
        cfg.setName("nameUpdate");
        cfg.setComment("comment");
        cfg.setPid(0L);
        cfg.setType("mdtrend");
        cfg.setTitle("test");
        cfg.setConfigure("");

        mvc.perform(put("/products/{productName}/widgets/{widgetName}", "productName", "name0")
                            .with(user("user").password("123456"))
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(serialize(cfg)))
                .andDo(print())
                .andExpect(jsonPath("$.data.productName", is("productName")))
                .andExpect(jsonPath("$.data.id", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("nameUpdate")))
                .andExpect(jsonPath("$.data.comment", is("comment")))
                .andExpect(jsonPath("$.data.version", is(1)))
                .andExpect(jsonPath("$.data.title", is("test")));
    }
    */

}