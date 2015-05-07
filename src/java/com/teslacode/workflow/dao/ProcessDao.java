package com.teslacode.workflow.dao;

import com.teslacode.workflow.model.Process;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dao Logic for Process
 *
 * @version 1
 * @author Ade Fruandta
 */
@Repository
@Transactional
public class ProcessDao extends Dao {

    /**
     * Save (Insert/Update) Process
     *
     * @param process
     * @return Process
     * @throws java.lang.Exception
     */
    public Process save(Process process) throws Exception {
        Session session = this.Open();
        session.beginTransaction();
        
        process = (Process) session.merge(process);

        session.getTransaction().commit();
        return process;
    }

    /**
     * Delete Process
     *
     * @param process
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean delete(Process process) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Boolean result;
        session.delete(process);
        result = true;

        session.getTransaction().commit();
        return result;
    }

    /**
     * Delete Process
     *
     * @param process
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean changeActive(Process process) throws Exception {
        Process oldProcess = getActiveVersion(process.getJobCode());
        oldProcess.setActive(Boolean.FALSE);
        save(oldProcess);

        process = get(process.getId());

        Session session = this.Open();
        session.beginTransaction();

        process.setActive(!process.getActive());
        Boolean result;
        session.update(process);
        result = true;

        session.getTransaction().commit();
        return result;
    }

    /**
     * Get All Process
     *
     * @return List Process
     * @throws Exception
     */
    public List<Process> get() throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List result;
        Criteria criteria = session.createCriteria(Process.class);
        criteria.addOrder(Order.asc("jobCode"));
        criteria.addOrder(Order.asc("version"));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }

    /**
     * Get Process By Job Code
     *
     * @param jobCode (Job Code)
     * @return List Process
     * @throws Exception
     */
    public List<Process> get(String jobCode) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        List<Process> result;
        Criteria criteria = session.createCriteria(Process.class);
        criteria.add(Restrictions.eq("jobCode", jobCode));
        criteria.addOrder(Order.asc("version"));
        result = criteria.list();

        session.getTransaction().commit();
        return result;
    }

    /**
     * Get Process By ID
     *
     * @param id (Process ID)
     * @return List Process
     * @throws Exception
     */
    public Process get(Integer id) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Process result = null;
        Criteria criteria = session.createCriteria(Process.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (Process) criteria.list().get(0);
        }

        session.getTransaction().commit();
        return result;
    }

    /**
     * Get Process By Job Code Only Active
     *
     * @param jobCode (Job Code)
     * @return List Process
     * @throws Exception
     */
    public Process getActiveVersion(String jobCode) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Process result = null;
        Criteria criteria = session.createCriteria(Process.class);
        criteria.add(Restrictions.eq("jobCode", jobCode));
        criteria.add(Restrictions.eq("active", true));
        criteria.addOrder(Order.asc("version"));
        if (!criteria.list().isEmpty()) {
            result = (Process) criteria.list().get(0);
        }

        session.getTransaction().commit();
        return result;
    }

    /**
     * Get Process By Job Code Last Version
     *
     * @param jobCode (Job Code)
     * @return List Process
     * @throws Exception
     */
    public Process getLastVersion(String jobCode) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Process result = null;
        Criteria criteria = session.createCriteria(Process.class);
        criteria.add(Restrictions.eq("jobCode", jobCode));
        criteria.addOrder(Order.desc("version"));
        if (!criteria.list().isEmpty()) {
            result = (Process) criteria.list().get(0);
        }

        session.getTransaction().commit();
        return result;
    }

}
