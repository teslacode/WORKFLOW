package com.teslacode.workflow.dao;

import com.teslacode.workflow.model.Header;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @version 1
 * @author Ade Fruandta
 */
public class HeaderDao extends Dao {
    
    /**
     * Save (Insert/Update) Header
     *
     * @param header
     * @return Header
     * @throws java.lang.Exception
     */
    public Header save(Header header) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        header = (Header) session.merge(header);

        session.getTransaction().commit();
        return header;
    }
    
    /**
     * Get Header By Id
     *
     * @param id (Header Id)
     * @return List Process
     * @throws Exception
     */
    public Header get(Integer id) throws Exception {
        Session session = this.Open();
        session.beginTransaction();

        Header result = null;
        Criteria criteria = session.createCriteria(Header.class);
        criteria.add(Restrictions.eq("id", id));
        if(!criteria.list().isEmpty()){
            result = (Header) criteria.list().get(0);
        }

        session.getTransaction().commit();
        return result;
    }
    
}
