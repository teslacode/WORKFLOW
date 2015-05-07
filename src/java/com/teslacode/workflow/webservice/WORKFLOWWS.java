package com.teslacode.workflow.webservice;

import com.teslacode.workflow.dao.ActivityDao;
import com.teslacode.workflow.dao.HeaderDao;
import com.teslacode.workflow.dao.NextFlagDao;
import com.teslacode.workflow.dao.ProcessDao;
import com.teslacode.workflow.model.Header;
import com.teslacode.workflow.model.Process;
import com.teslacode.workflow.model.Activity;
import com.teslacode.workflow.model.NextFlag;
import com.teslacode.workflow.model.Rule;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Workflow All Function
 *
 * @version 1
 * @author Ade Fruandta
 */
@WebService(serviceName = "WORKFLOWWS")
public class WORKFLOWWS {

    @Autowired
    private final HeaderDao headerDao = new HeaderDao();
    @Autowired
    private final ProcessDao processDao = new ProcessDao();
    @Autowired
    private final ActivityDao activityDao = new ActivityDao();
    @Autowired
    private final NextFlagDao nextFlagDao = new NextFlagDao();

    /**
     * Create New Workflow
     *
     * @param job
     * @param nextFlag
     * @return
     */
    @WebMethod(operationName = "create")
    public Integer create(@WebParam(name = "job") String job, @WebParam(name = "nextFlag") String nextFlag) {
        Header header = new Header();
        try {
            Process process = processDao.getActiveVersion(job);
            List<Activity> listActivity = activityDao.getByProcessId(process.getId());
            Activity activity = listActivity.get(0);

            header.setJobCode(job);
            header.setActivityId(activity.getId());
            header = headerDao.save(header);

            if (nextFlag != null && !nextFlag.isEmpty()) {
                next(header.getId(), nextFlag);
            }
        } catch (Exception ex) {
            Logger.getLogger(WORKFLOWWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return header.getId();
    }

    /**
     * Next Workflow
     *
     * @param headerId
     * @param flag
     * @return (True/False)
     */
    @WebMethod(operationName = "next")
    public Boolean next(@WebParam(name = "headerId") Integer headerId, @WebParam(name = "flag") String flag) {
        Boolean result = false;
        try {
            Header header = headerDao.get(headerId);
            NextFlag nextFlag = nextFlagDao.getByFromActivityId(header.getActivityId(), flag);

            header.setPrevActivityId(header.getActivityId());
            header.setActivityId(nextFlag.getToActivityId());
            header.setUpdateDate(new Date());

            headerDao.save(header);

            result = true;
        } catch (Exception ex) {
            Logger.getLogger(WORKFLOWWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Validation Rule Activity
     *
     * @param rule
     * @return Result Validation
     */
    @WebMethod(operationName = "validateActivityRule")
    public Rule validateActivityRule(@WebParam(name = "rule") Rule rule) {
        Map<String, String> result = new HashMap<>();
        try {
            Activity activity = activityDao.get(rule.getActivityId());
            result = Rule.getResult(rule.getData(), activity.getRules());
        } catch (Exception ex) {
            Logger.getLogger(WORKFLOWWS.class.getName()).log(Level.SEVERE, null, ex);
            result.put("result", "Error");
            result.put("message", ex.toString());
        }
        rule.setResult(result);
        return rule;
    }

    /**
     * Get Header
     *
     * @param headerId
     * @return Header
     */
    @WebMethod(operationName = "getHeader")
    public Header getHeader(@WebParam(name = "headerId") Integer headerId) {
        Header header = null;
        try {
            header = headerDao.get(headerId);
        } catch (Exception ex) {
            Logger.getLogger(WORKFLOWWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return header;
    }

    /**
     * Get List Next Flag
     *
     * @param headerId
     * @param withHide
     * @return Header
     */
    @WebMethod(operationName = "getListFlag")
    public List<NextFlag> getListFlag(@WebParam(name = "headerId") Integer headerId, @WebParam(name = "withHide") Boolean withHide) {
        List<NextFlag> listNextFlag = null;
        try {
            Header header = headerDao.get(headerId);
            listNextFlag = (withHide ? nextFlagDao.getByFromActivityId(header.getActivityId()) : nextFlagDao.getByFromActivityId(header.getActivityId(), false));
        } catch (Exception ex) {
            Logger.getLogger(WORKFLOWWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNextFlag;
    }
}
