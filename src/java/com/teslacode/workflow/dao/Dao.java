package com.teslacode.workflow.dao;

import com.teslacode.workflow.service.HibernateUtil;
import org.hibernate.Session;

/**
 * Common Dao
 * 
 * @version 1
 * @author Ade Fruandta
 */
public class Dao {
    
    /**
     * Open Connection
     * 
     * @return Session
     * @throws java.lang.Exception
     */
    public Session Open() throws Exception {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}
