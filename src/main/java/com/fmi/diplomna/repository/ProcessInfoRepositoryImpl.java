/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.diplomna.repository;

import com.fmi.diplomna.domain.mongo.ProcessData;
import com.fmi.diplomna.dto.ProcessListMessage;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProcessInfoRepositoryImpl extends GenericMongoRepository<ProcessData> implements ProcessInfoRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProcessInfoRepositoryImpl.class);

    
}
