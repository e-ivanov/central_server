/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.repository;

import com.fmi.diplomna.cache.EmailNotificatonCache;
import com.fmi.diplomna.hibernate.AppInfo;
import com.fmi.diplomna.hibernate.EmailNotification;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
public class EmailNotificationRepositoryImpl extends GenericCRUDRepository<EmailNotification>implements EmailNotificationRepository {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationRepositoryImpl.class);

}
