/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.eivanov.centralserver.thesis.listeners.StatisticsInfoListener;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author killer
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
@ComponentScan(basePackages = {"com.eivanov.centralserver.thesis.*"},
        excludeFilters = {@ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
                          @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION)})
public class ServiceConfig {
    
    @Autowired
    private Environment env;
    
    @Bean
    public DataSource dataSource() {

        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(100);
        ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        ds.addDataSourceProperty("url", env.getProperty("mysql.connection_string"));
        ds.addDataSourceProperty("user", env.getProperty("mysql.user"));
        ds.addDataSourceProperty("password", env.getProperty("mysql.password"));
        ds.addDataSourceProperty("cachePrepStmts", true);
        ds.addDataSourceProperty("prepStmtCacheSize", 250);
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        ds.addDataSourceProperty("useServerPrepStmts", true);
        ds.addDataSourceProperty("characterEncoding","utf8");
        ds.addDataSourceProperty("useUnicode","true");
        return ds;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transaconManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
    

    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceponTranslaon() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        lsfb.setPackagesToScan(new String[]{"com.eivanov.centralserver.thesis.entity"});
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        lsfb.setHibernateProperties(props);
        return lsfb;
    }

    @Bean
    ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
    
     @Bean
    public Scheduler getQuartzFactory() {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = factory.getScheduler();
            scheduler.start();
        } catch (SchedulerException ex) {
            Logger.getLogger(MainConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scheduler;
    }
    
    
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty("rabbitmq.host"));
        connectionFactory.setUsername(env.getProperty("rabbitmq.user"));
        connectionFactory.setPassword(env.getProperty("rabbitmq.password"));
        return connectionFactory;
    }
    
    @Bean 
    public RabbitTemplate getRabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange(env.getProperty("rabbitmq.notification_exchange"));
        template.setQueue(env.getProperty("rabbitmq.notification_queue"));
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
    
     @Bean
    public AmqpAdmin getAdmin() {
        AmqpAdmin admin = new RabbitAdmin(connectionFactory());
        admin.declareQueue(statisticsQueue());
        return admin;
    }

    @Qualifier("statistics_queue")
    @Bean
    public Queue statisticsQueue() {
        return new Queue(env.getProperty("rabbitmq.queuename"), false, true, true);
    }
    
    @Qualifier("notification_queue")
    @Bean
    public Queue notificationQueue(){
        return new Queue(env.getProperty("rabbitmq.notification_queue"), true, false, false);
    }
    
    @Qualifier("statistics_exchange")
    @Bean
    public DirectExchange getExchange() {
        return new DirectExchange(env.getProperty("rabbitmq.exchange_name"), false, false);
    }
    
    @Qualifier("notification_exchange")
    @Bean
    public DirectExchange getNotificationExchange(){
        return new DirectExchange(env.getProperty("rabbitmq.notification_exchange"), true, false);
    }

    @Bean
    Binding getBinding(@Qualifier("statistics_queue")Queue queue, @Qualifier("statistics_exchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).withQueueName();
    }
    
    @Bean 
    Binding getNotificationBinding(@Qualifier("notification_queue")Queue queue, @Qualifier("notification_exchange")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).withQueueName();
    }

    @Bean
    public MessageListener getListener() {
        return new StatisticsInfoListener();
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer(@Qualifier("statistics_queue")Queue queue) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory());
        listenerContainer.setQueues(queue);
//        listenerContainer.setQueueNames(queueName);
        listenerContainer.setMessageConverter(jsonMessageConverter());
        listenerContainer.setMessageListener(getListener());
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
        listenerContainer.setErrorHandler(
                new ConditionalRejectingErrorHandler(
                        new ServiceConfig.InvalidPayloadRejectingFatalExceptionStrategy()));
        return listenerContainer;
    }
    
        @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    
    static class InvalidPayloadRejectingFatalExceptionStrategy implements FatalExceptionStrategy {

        private static final org.slf4j.Logger logger = LoggerFactory.getLogger(InvalidPayloadRejectingFatalExceptionStrategy.class);

        @Override
        public boolean isFatal(Throwable t) {
            if (t instanceof ListenerExecutionFailedException
                    && (t.getCause() instanceof MessageConversionException
                    || t.getCause() instanceof MethodArgumentNotValidException)) {
                logger.warn("Fatal message conversion error; message rejected; it will be dropped: {}",
                        ((ListenerExecutionFailedException) t).getFailedMessage());
                return true;
            }
            return false;
        }
    }

}
