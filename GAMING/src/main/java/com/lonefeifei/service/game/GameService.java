/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.service.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lonefeifei.domain.entity.Game;
import com.lonefeifei.domain.repository.GameRepository;

/**
 * Created by baidu on 16/8/25.
 */
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Game> getGames() {
        return gameRepository.findAll();
    }
}
