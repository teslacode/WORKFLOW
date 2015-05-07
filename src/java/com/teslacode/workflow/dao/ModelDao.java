package com.teslacode.workflow.dao;

import com.teslacode.workflow.model.Model;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dao Logic for Model
 *
 * @version 1
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class ModelDao extends Dao {
    
    /**
     * Save (Insert/Update) Model
     *
     * @param model
     * @return Model
     * @throws java.lang.Exception
     */
    public Model save(Model model) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        model = (Model) session.merge(model);

        session.getTransaction().commit();
        return model;
    }

    /**
     * Delete Model
     *
     * @param model
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean delete(Model model) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Boolean result;
        session.delete(model);
        result = true;

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get Model All
     *
     * @return List Model
     * @throws Exception
     */
    public List<Model> get() throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List<Model> result;
        Criteria criteria = session.createCriteria(Model.class);
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get Model By Id
     *
     * @param id (Model Id)
     * @return List Model
     * @throws Exception
     */
    public Model get(Integer id) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Model result = null;
        Criteria criteria = session.createCriteria(Model.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (Model) criteria.list().get(0);
        }

        session.getTransaction().commit();
        return result;
    }
    
    /**
     * Get Model By Process Id
     *
     * @param processId (Process Id)
     * @return List Model
     * @throws Exception
     */
    public List<Model> getByProcessId(Integer processId) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List<Model> result;
        Criteria criteria = session.createCriteria(Model.class);
        criteria.add(Restrictions.eq("processId", processId));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }
    
}
