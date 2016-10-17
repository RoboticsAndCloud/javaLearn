package com.lonefeifei.custom.Scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.config.ContextLifecycleScheduledTaskRegistrar;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by baidu on 16/9/18.
 */
@Component
@EnableScheduling
public class SyncTasksManager {
//    @Autowired
//    private ContextLifecycleScheduledTaskRegistrar taskRegistrar;
    private ContextLifecycleScheduledTaskRegistrar taskRegistrar = new ContextLifecycleScheduledTaskRegistrar();

    @PostConstruct
    protected void init() {
//        LOG.debug("Start to schedule doc sync working.");
//
//        AppProperties.DocConfiguration docConfig = properties.getDocs();
//        if (docConfig == null) {
//            LOG.debug("Doc sync working not configured.");
//            return;
//        }
//
//        if (!docConfig.isEnabled()) {
//            LOG.debug("Doc sync function disabled.");
//            return;
//        }
//
//        if (isEmpty(docConfig.getSyncCron())) {
//            LOG.debug("Doc sync working not configured with a cron schedule.");
//            return;
//        }
//
//        File docFolder = buildDocFolder();
//        DocSyncGitTask task = new DocSyncGitTask(docFolder, properties.getDocs());
        // make the task run on app startup
//        task.run();
        taskRegistrar.addCronTask(new CountTask(),"0/5 * * * * *");
    }
}

class CountTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(CountTask.class);
    @Override
    public void run() {
        LOG.info(">>>>>>>>>>>>>CountTask scheduled  run... ");
    }
}
