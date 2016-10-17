/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.configuration.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by baidu on 16/8/23.
 */

@Component
@ConfigurationProperties(prefix = "project")
//@EnableConfigurationProperties
public class AppProperties {

//    private AppInfo info = new AppInfo();
    @Autowired
    private AppInfo info;

    public AppInfo getInfo() {
        return info;
    }

    public AppProperties setInfo(AppInfo info) {
        this.info = info;
        return this;
    }

//    @Value("downlaod")
    @Value("${spring.datasource.url}")
//    @Value("#{ T(java.lang.Math).random() *100 }")
    private String downloadFolder;

    public String getDownloadFolder() {
        return downloadFolder;
    }

    public AppProperties setDownloadFolder(String downloadFolder) {
        this.downloadFolder = downloadFolder;
        return this;
    }
}