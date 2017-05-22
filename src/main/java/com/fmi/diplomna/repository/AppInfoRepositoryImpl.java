/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.repository;

import com.fmi.diplomna.cache.AppInfoCache;
import com.fmi.diplomna.hibernate.AppInfo;
import com.fmi.diplomna.hibernate.User;
import com.fmi.diplomna.scheduler.PingJob;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.quartz.JobBuilder;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
public class AppInfoRepositoryImpl extends GenericCRUDRepository<AppInfo>implements AppInfoRepository {

    private static final Logger logger = LoggerFactory.getLogger(AppInfoRepositoryImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Autowired
    protected HibernateTransactionManager txManager;


    @Autowired
    private Scheduler scheduler;



    @Override
    public void save(AppInfo entity) {
        boolean isNewEntity = entity.getId() == null;
        super.save(entity);
        if (!isNewEntity) {
            try {
                updateJob(entity);
            } catch (SchedulerException ex) {
                logger.error(ex.getMessage());
            }
        } else {
            try {
                addNewJob(entity);
            } catch (SchedulerException ex) {
                logger.error(ex.getMessage());
            }
        }

    }

    private void addNewJob(AppInfo job) throws SchedulerException {
        assembleNewJob(job);
    }

    @Override
    public AppInfo load(long id) {
        return (AppInfo) super.load(id);
    }

    @Override
    public void delete(AppInfo entity) {
        super.delete(entity);
//        try {
//            schedulersRepository.removeJob(entity);
//        } catch (SchedulerException ex) {
//            java.util.logging.Logger.getLogger(AppInfoRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public List<AppInfo> loadAll() {
        return super.loadAll();
    }

    @Override
    public void saveAll(List<AppInfo> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void assembleNewJob(AppInfo job) throws SchedulerException {

        JobDetail jobDetail = setJobDetails(job);
        Trigger trig = setTriggerDetails(job);

        scheduler.scheduleJob(jobDetail, trig);
        logger.info("Added job with id: " + job.getId() + " to the scheduler");
    }

    private void updateJob(AppInfo job) throws SchedulerException {
        logger.info("Updating job");
        JobDetail jobDetail = setJobDetails(job);
        Trigger trig = setTriggerDetails(job);
        scheduler.addJob(jobDetail, true, true);
        scheduler.rescheduleJob(TriggerKey.triggerKey(String.valueOf(job.getId())), trig);

    }

    private JobDetail setJobDetails(AppInfo job) {
        JobBuilder builder = newJob(PingJob.class);
        builder.withIdentity(String.valueOf(job.getId()));
        builder.usingJobData("protocol", job.getProtocol());
        builder.usingJobData("url", job.getUrl());
        builder.usingJobData("path", job.getPath());
        builder.usingJobData("port", job.getPort());
        builder.usingJobData("hearthbeatInterval", job.getHearthbeatInterval());
        builder.usingJobData("unresponsiveInterval", job.getUnresponsiveInterval());
        builder.usingJobData("secondsBetweenNotifications", job.getNotificationInterval());
        JobDetail jobDetail = builder.build();
        return jobDetail;
    }

    private Trigger setTriggerDetails(AppInfo job) {
        Trigger trig = newTrigger()
                .withIdentity(String.valueOf(job.getId()))
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(job.getHearthbeatInterval())
                        .repeatForever())
                .build();
        return trig;
    }

}
