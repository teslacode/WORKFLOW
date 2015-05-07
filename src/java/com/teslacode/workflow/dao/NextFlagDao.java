package com.teslacode.workflow.dao;

import com.teslacode.workflow.model.NextFlag;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dao Logic for Next Flag
 * 
 * @version 1
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class NextFlagDao extends Dao {
    
    /**
     * Save (Insert/Update) NextFlag
     *
     * @param nextFlag
     * @return NextFlag
     * @throws java.lang.Exception
     */
    public NextFlag save(NextFlag nextFlag) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        nextFlag = (NextFlag) session.merge(nextFlag);

        session.getTransaction().commit();
        return nextFlag;
    }
    
    /**
     * Delete NextFlag
     *
     * @param nextFlag
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean delete(NextFlag nextFlag) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Boolean result;
        session.delete(nextFlag);
        result = true;

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get All NextFlag
     *
     * @return List NextFlag
     * @throws Exception
     */
    public List<NextFlag> get() throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(NextFlag.class);
        criteria.addOrder(Order.asc("id"));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get NextFlag By Id
     *
     * @param id (Next Flag Id)
     * @return List NextFlag
     * @throws Exception
     */
    public NextFlag get(Integer id) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        NextFlag result = null;
        Criteria criteria = session.createCriteria(NextFlag.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (NextFlag) criteria.list().get(0);
        }

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get List NextFlag By Process Id
     *
     * @param processId (Process Id)
     * @return List NextFlag
     * @throws Exception
     */
    public List<NextFlag> getByProcessId(Integer processId) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(NextFlag.class);
        criteria.createAlias("fromActivity", "fromActivity");
        criteria.createAlias("toActivity", "toActivity");
        criteria.add(Restrictions.eq("fromActivity.processId", processId));
        criteria.add(Restrictions.eq("toActivity.processId", processId));
        criteria.addOrder(Order.asc("fromActivity.order"));
        criteria.addOrder(Order.asc("order"));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get List NextFlag By From Activity Id
     *
     * @param fromActivityId (From Activity Id)
     * @return List NextFlag
     * @throws Exception
     */
    public List<NextFlag> getByFromActivityId(Integer fromActivityId) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(NextFlag.class);
        criteria.add(Restrictions.eq("fromActivityId", fromActivityId));
        criteria.addOrder(Order.asc("order"));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get List NextFlag By From Activity Id
     *
     * @param fromActivityId (From Activity Id)
     * @param hide
     * @return List NextFlag
     * @throws Exception
     */
    public List<NextFlag> getByFromActivityId(Integer fromActivityId, Boolean hide) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(NextFlag.class);
        criteria.add(Restrictions.eq("fromActivityId", fromActivityId));
        criteria.add(Restrictions.eq("hide", hide));
        criteria.addOrder(Order.asc("order"));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get List NextFlag By From Activity Id
     *
     * @param fromActivityId (From Activity Id)
     * @param flag (Next Flag)
     * @return List NextFlag
     * @throws Exception
     */
    public NextFlag getByFromActivityId(Integer fromActivityId, String flag) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        NextFlag result = null;
        Criteria criteria = session.createCriteria(NextFlag.class);
        criteria.add(Restrictions.eq("fromActivityId", fromActivityId));
        criteria.add(Restrictions.eq("flag", flag));
        if(!criteria.list().isEmpty()){
            result = (NextFlag) criteria.list().get(0);
        }

        session.getTransaction().commit();
        return result;
    }
    
}
