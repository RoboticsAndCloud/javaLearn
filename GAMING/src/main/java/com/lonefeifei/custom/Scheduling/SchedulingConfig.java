package com.lonefeifei.custom.Scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ContextLifecycleScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

/**
 * 定时任务配置类
 *
 * @author lf(365384722)
 * @create 2016年9月18日
 */
//@Configuration
//@EnableScheduling // 启用定时任务
//@Component
public class SchedulingConfig {
    private static final Logger LOG = LoggerFactory.getLogger(SchedulingConfig.class);

    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
//    @Scheduled(fixedRate = 5000)
//    @Scheduled(fixedDelay = 5000)
    public void scheduler() {
        LOG.info(">>>>>>>>>>>>> scheduled ... ");
    }

//    @Bean
//    public ContextLifecycleScheduledTaskRegistrar taskRegistrar() {
//        return new ContextLifecycleScheduledTaskRegistrar();
//    }

}