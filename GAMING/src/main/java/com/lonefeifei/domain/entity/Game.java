/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.domain.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

//import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by baidu on 16/8/17.
 */
@Entity(name = "game")
@Table(
       indexes = {
                  @Index(name = "game_zone_name_unq_idx", columnList = "`id`, `zone`, `name`", unique = true),
       }
)
@EntityListeners({AuditingEntityListener.class})
@NamedQuery(name = "Game.findByNamedQuery",
        query = "select g from game g")
public class Game extends AbstractAuditable<Game, Long> {
    @Id
    @GeneratedValue
    private Long id;

    private String zone = "default";


    @NotNull
    @NotBlank
    @Column(
                   name = "`name`",
                   length = 128,
                   nullable = false
    )
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

    @JsonIgnore
    @Basic(optional = false)
    @Lob
    private String content;

    @JsonIgnore
    @Column(length = 1024)
    private String comment;

    // 赌局状态： on,closed,waiting
    private String status;

    // 赌局金额
    private int money;

    //todo  历史信息记录 lastModify lastModifyUser

    public String getUser() {
        return user;
    }

    public Game setUser(String user) {
        this.user = user;
        return this;
    }

    public String getLastModeiUser() {
        return lastModeiUser;
    }

    public Game setLastModeiUser(String lastModeiUser) {
        this.lastModeiUser = lastModeiUser;
        return this;
    }


    public Game setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public long getVersion() {
        return version;
    }

    public Game setVersion(long version) {
        this.version = version;
        return this;
    }

    @CreatedBy
    private String user;

    @LastModifiedBy
    private String lastModeiUser;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date lastModifiedDate;
    @Version
    @JsonIgnore
    private long version;


    public Long getId() {
        return id;
    }

    public String getZone() {
        return zone;
    }

    public Game setZone(String zone) {
        this.zone = zone;
        return this;
    }

    public String getName() {
        return name;
    }

    public Game setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Game setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getMaxParticipantCount() {
        return maxParticipantCount;
    }

    public Game setMaxParticipantCount(int maxParticipantCount) {
        this.maxParticipantCount = maxParticipantCount;
        return this;
    }

    public int getParticipantCount() {
        return participantCount;
    }

    public Game setParticipantCount(int participantCount) {
        this.participantCount = participantCount;
        return this;
    }

    public String getBetType() {
        return betType;
    }

    public Game setBetType(String betType) {
        this.betType = betType;
        return this;
    }

    public String getContestType() {
        return contestType;
    }

    public Game setContestType(String contestType) {
        this.contestType = contestType;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Game setContent(String content) {
        this.content = content;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Game setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Game setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getMoney() {
        return money;
    }

    public Game setMoney(int money) {
        this.money = money;
        return this;
    }

    @PostPersist
    public void setName() {
        String defaultName = this.getDefaultName();
        if(!StringUtils.hasText(this.getName()) || this.getName().equals(defaultName)) {
            this.setName(String.format("%s_%d", new Object[]{defaultName, this.getId()}));
        }

    }

    @PrePersist
    public void setDefaultName() {
        if(!StringUtils.hasText(this.getName())) {
            this.setName(this.getDefaultName());
        }

    }

    private String getDefaultName() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}
