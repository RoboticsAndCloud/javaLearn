/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.domain.repository;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lonefeifei.domain.entity.Game;

/**
 * Created by baidu on 16/8/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void testFindByProductName() throws Exception {
        Game game = new Game();
        game.setName("test");

        Game save = gameRepository.save(game);
        System.out.println(save.getName());
        assertNotNull(save.getName());
        assertEquals(format("%s_%d", "configure", save.getId()), save.getName());
    }
}
