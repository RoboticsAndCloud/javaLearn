/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.custom.CommandLineRunner;

/**
 * Created by baidu on 16/10/14.
 */

import com.lonefeifei.domain.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by baidu on 16/7/24.
 */
@Component
public class StartupRunner implements CommandLineRunner {
    protected final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Autowired
    private DataSource ds;

    @Autowired
    private GameRepository gameRepository;


    @Override
    public void run(String... strings) throws Exception {

//        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        logger.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        logger.info("Datasource: " + ds.toString());
        logger.info("Number of game: " + gameRepository.count());
    }
}