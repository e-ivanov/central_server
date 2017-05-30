/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmi.diplomna.converters.AppInfoConverter;
import com.fmi.diplomna.converters.DateTimeConverter;
import com.fmi.diplomna.converters.NotificationChannelConvertor;
import com.fmi.diplomna.converters.NotificationGroupConvertor;
import com.fmi.diplomna.converters.ResourcePolicyConverter;
import com.fmi.diplomna.converters.ServerConverter;
import com.fmi.diplomna.converters.UserConverter;
import com.fmi.diplomna.converters.UserRoleConvertor;

import com.fmi.diplomna.listeners.StatisticsInfoListener;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.format.FormatterRegistry;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author killer
 */
@Configuration
@ComponentScan(basePackages = {"com.fmi.diplomna.web.*"})
@EnableWebMvc
//@EnableTransactionManagement
@PropertySource("classpath:config.properties")
public class MainConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Bean
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("/static/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getResourcePolicyConverter());
        registry.addConverter(getServerConverter());
        registry.addConverter(getUserConverter());
        registry.addConverter(getAppInfoConverter());
        registry.addConverter(getNotificationGroupConvertor());
        registry.addConverter(getJodaDateTimeConverter());
        registry.addConverter(getUserRoleConvertor());
        registry.addConverter(getNotificationChannelConverter());
    }

    @Bean
    public NotificationChannelConvertor getNotificationChannelConverter(){
        return new NotificationChannelConvertor();
    }
    
    @Bean
    public ResourcePolicyConverter getResourcePolicyConverter() {
        return new ResourcePolicyConverter();
    }

    @Bean
    public DateTimeConverter getJodaDateTimeConverter() {
        return new DateTimeConverter();
    }
    
    @Bean
    public UserRoleConvertor getUserRoleConvertor(){
        return new UserRoleConvertor();
    }

    @Bean
    public ServerConverter getServerConverter() {
        return new ServerConverter();
    }

    @Bean
    public UserConverter getUserConverter() {
        return new UserConverter();
    }

    @Bean
    public AppInfoConverter getAppInfoConverter() {
        return new AppInfoConverter();
    }

    @Bean
    public NotificationGroupConvertor getNotificationGroupConvertor() {
        return new NotificationGroupConvertor();
    }

}
