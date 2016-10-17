/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.mvc;

import com.lonefeifei.configuration.properties.AppInfo;
import com.lonefeifei.configuration.properties.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by baidu on 16/8/12.
 */
@Controller
public class SimpleController {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleController.class);

//    @Autowired
//    private AppInfo appInfo;

    @Autowired
    private AppProperties appProperties;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloInfo() {
        LOG.debug("SimpleController info");
        return "Hello Gaming";
    }

    @RequestMapping(value = "appInfo", method = RequestMethod.GET)
    @ResponseBody
    public AppInfo appInfo() {
        LOG.debug("SimpleController info");
        return appProperties.getInfo();
    }

    @RequestMapping(value = "appProperties", method = RequestMethod.GET)
    @ResponseBody
    public String appProperties() {
        LOG.debug("SimpleController info");
        return appProperties.getDownloadFolder();
    }

}
