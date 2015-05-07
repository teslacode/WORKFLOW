package com.teslacode.workflow.dao;

import com.teslacode.workflow.model.Job;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dao Logic for Job
 * 
 * @version 1
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class JobDao extends Dao {
    
    /**
     * Save (Insert/Update) Job
     *
     * @param job
     * @return Job
     * @throws java.lang.Exception
     */
    public Job save(Job job) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        job = (Job) session.merge(job);

        session.getTransaction().commit();
        return job;
    }
    
    /**
     * Delete Job
     *
     * @param job
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean delete(Job job) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Boolean result;
        session.delete(job);
        result = true;

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get All Job
     *
     * @return List Job
     * @throws Exception
     */
    public List<Job> get() throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(Job.class);
        criteria.addOrder(Order.asc("code"));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get Job By Code
     *
     * @param code (Job Code)
     * @return Job
     * @throws Exception
     */
    public Job get(String code) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Job result = null;
        Criteria criteria = session.createCriteria(Job.class);
        criteria.add(Restrictions.eq("code", code));
        criteria.addOrder(Order.asc("code"));
        if(!criteria.list().isEmpty()){
            result = (Job) criteria.list().get(0);
        }

        session.getTransaction().commit();
        return result;
    }
    
}
