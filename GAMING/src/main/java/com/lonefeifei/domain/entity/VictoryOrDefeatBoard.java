/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//import com.sun.istack.internal.NotNull;

/**
 * Created by baidu on 16/8/17.
 */
@Entity
public class VictoryOrDefeatBoard {
    @Id
    @GeneratedValue
    private Long id;

//    @NotNull
    private Long gameId;

//    @NotNull
    private Long participantId;

    // 胜者信息
    private String victoryContent;
    // 败者信息
    private String defeatedContent;

    private int money;

    // 赌局结果：胜win，负lose，无效invalid
    private String result;

}
