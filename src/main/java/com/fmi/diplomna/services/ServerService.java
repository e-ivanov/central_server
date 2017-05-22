/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.services;

import com.fmi.diplomna.hibernate.Server;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author killer
 */
public interface ServerService {

    public void save(Server entity);

    public Server load(long id);
  
    public void delete(Server entity);


    public void deleteAll();

    public List<Server> loadAll();

    public void saveAll(List<Server> items);
}
