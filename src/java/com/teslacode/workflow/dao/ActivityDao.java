package com.teslacode.workflow.dao;

import com.teslacode.workflow.model.Activity;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dao Logic for Activity
 * 
 * @version 1
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class ActivityDao extends Dao {
    
    /**
     * Save (Insert/Update) Activity
     *
     * @param activity
     * @return Activity
     * @throws java.lang.Exception
     */
    public Activity save(Activity activity) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        activity = (Activity) session.merge(activity);

        session.getTransaction().commit();
        return activity;
    }
    
    /**
     * Delete Activity
     *
     * @param activity
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean delete(Activity activity) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Boolean result;
        session.delete(activity);
        result = true;

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get All Activity
     *
     * @return List Activity
     * @throws Exception
     */
    public List<Activity> get() throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(Activity.class);
        criteria.addOrder(Order.asc("id"));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get All Activity
     *
     * @param id
     * @return List Activity
     * @throws Exception
     */
    public Activity get(Integer id) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Activity result = null;
        Criteria criteria = session.createCriteria(Activity.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (Activity) criteria.list().get(0);
        }        

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get List Activity By Process Id
     *
     * @param processId (Process Id)
     * @return List Activity
     * @throws Exception
     */
    public List<Activity> getByProcessId(Integer processId) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(Activity.class);
        criteria.add(Restrictions.eq("processId", processId));
        criteria.addOrder(Order.asc("order"));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get Activity Similar
     *
     * @param activity
     * @return Activity Similar
     * @throws Exception
     */
    public Activity getSimilar(Activity activity) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Activity result = null;
        Criteria criteria = session.createCriteria(Activity.class);
        criteria.add(Restrictions.eq("path", activity.getPath()));
        criteria.add(Restrictions.eq("processId", activity.getProcessId()));
        criteria.addOrder(Order.asc("order"));
        if(!criteria.list().isEmpty()){
            result = (Activity) criteria.list().get(0);
        }

        session.getTransaction().commit();
        return result;
    }
    
}
