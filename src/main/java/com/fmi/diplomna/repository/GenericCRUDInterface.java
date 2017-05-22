/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.repository;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author killer
 */
public interface GenericCRUDInterface<T> {
    public void save(T entity);
    public T load(long id);
    public void delete (T entity);
    public void deleteAll(List<T> entities);
    public List<T> loadAll();
    public void saveAll(List<T> items);

}
