/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author killer
 */
public class GenericCRUDRepository<T> implements GenericCRUDInterface<T>{
    
    private final Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void save(T entity){
        T result =  (T)getCurrentSession().merge(entity);
        getCurrentSession().saveOrUpdate(result);
    }
    public  T load(long id){
        return (T)getCurrentSession().get(clazz, id);
    }
    public void delete (T entity){
        T result =  (T)getCurrentSession().merge(entity);
        getCurrentSession().delete(result);
    }
    public void deleteAll(List<T> entities){
        Session session = getCurrentSession();
        for(T item : entities){
            session.delete(item);
        }
    }
    public List<T> loadAll(){
        List<T> list = getCurrentSession().createQuery("from "+clazz.getName()).list();
        return list;
    }
    public void saveAll(List<T> items){
        //Not implemented yet
    }
    
    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    
}
