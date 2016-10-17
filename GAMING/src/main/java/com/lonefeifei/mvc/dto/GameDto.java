/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.mvc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lonefeifei.domain.entity.Game;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * Created by baidu on 16/8/25.
 */
public class GameDto implements Serializable {
    private static final long serialVersionUID = -3625859432446293602L;
    private String zone = "default";

    @NotNull
    @NotBlank
    private String name;

    private String title;
    // 最大参与人数
    private int maxParticipantCount;
    // 已参与人数
    private int participantCount;

    // 赌局类型：胜负局，大小局，分差局
    private String betType;

    // 赛事类型，如篮球，足球
    private String contestType;


    private String content;

    @JsonIgnore
    private String comment;

    // 赌局状态： on,closed,waiting
    private String status;

    // 赌局金额
    private int money;


    public GameDto fromModel(Game model) {
//        super.fromModel(model);
        this.betType = model.getBetType();
        this.comment = model.getComment();
        this.content = model.getContent();
        this.maxParticipantCount = model.getMaxParticipantCount();
        this.money = model.getMoney();
        this.name = model.getName();
        this.participantCount = model.getParticipantCount();
        this.status = model.getStatus();
        this.zone = model.getZone();
        this.title = model.getTitle();
        this.contestType = model.getContestType();
        return this;
    }

    public void toModel(Game model) {
//        super.toModel(model);
        model.setBetType(this.betType);
        model.setComment(this.comment);
        model.setContent(this.content);
        model.setMaxParticipantCount(this.maxParticipantCount);
        model.setMoney(this.money);
        model.setName(this.name);
        model.setParticipantCount(this.participantCount);
        model.setStatus(this.status);
        model.setZone(this.zone);
        model.setTitle(this.title);
        model.setContestType(this.contestType);
    }

    public String getZone() {
        return zone;
    }

    public GameDto setZone(String zone) {
        this.zone = zone;
        return this;
    }

    public String getName() {
        return name;
    }

    public GameDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GameDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getMaxParticipantCount() {
        return maxParticipantCount;
    }

    public GameDto setMaxParticipantCount(int maxParticipantCount) {
        this.maxParticipantCount = maxParticipantCount;
        return this;
    }

    public int getParticipantCount() {
        return participantCount;
    }

    public GameDto setParticipantCount(int participantCount) {
        this.participantCount = participantCount;
        return this;
    }

    public String getBetType() {
        return betType;
    }

    public GameDto setBetType(String betType) {
        this.betType = betType;
        return this;
    }

    public String getContestType() {
        return contestType;
    }

    public GameDto setContestType(String contestType) {
        this.contestType = contestType;
        return this;
    }

    public String getContent() {
        return content;
    }

    public GameDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public GameDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public GameDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getMoney() {
        return money;
    }

    public GameDto setMoney(int money) {
        this.money = money;
        return this;
    }
}
