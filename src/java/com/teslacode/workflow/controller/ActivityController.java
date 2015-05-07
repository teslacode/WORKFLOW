package com.teslacode.workflow.controller;

import com.teslacode.workflow.dao.ActivityDao;
import com.teslacode.workflow.model.Activity;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Class Activity Controller
 *
 * @version 1
 * @author Ade Fruandta
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityDao activityDao;

    /**
     * Default Constructor
     */
    public ActivityController() {

    }

    /**
     * Get All List Activity
     *
     * @return List Activity
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Activity> get() throws ParseException {
        List<Activity> listActivity = new ArrayList<>();
        try {
            listActivity = activityDao.get();
        } catch (Exception ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listActivity;
    }

    /**
     * Get Activity by Process Id
     *
     * @param id (Activity Id)
     * @return List Activity
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, params = {"id"})
    @ResponseBody
    public Activity get(@RequestParam(value = "id", required = true) Integer id) throws ParseException {
        Activity activity = new Activity();
        try {
            activity = activityDao.get(id);
        } catch (Exception ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activity;
    }
    
    /**
     * Get Activity by Process Id
     *
     * @param id
     * @param processId (Process Id)
     * @return List Activity
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, params = {"id","processId"})
    @ResponseBody
    public List<Activity> get(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "processId", required = true) Integer processId) throws ParseException {
        List<Activity> listActivity = new ArrayList<>();
        try {
            listActivity = activityDao.getByProcessId(processId);
        } catch (Exception ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listActivity;
    }

    /**
     * Get Activity by Process Id
     *
     * @param processId (Process Id)
     * @return List Activity
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, params = {"processId"})
    @ResponseBody
    public List<Activity> getByProcessId(@RequestParam(value = "processId", required = true) Integer processId) throws ParseException {
        List<Activity> listActivity = new ArrayList<>();
        try {
            listActivity = activityDao.getByProcessId(processId);
        } catch (Exception ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listActivity;
    }

    /**
     * Save Activity
     *
     * @param activity
     * @return Activity
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(@ModelAttribute("activity") Activity activity) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            if (activity.getId() != null) {
                Activity newActivity = activityDao.get(activity.getId());
                newActivity.setUpdateDate(new Date());
                if (activity.getOrder() != null) {
                    newActivity.setOrder(activity.getOrder());
                }
                if (activity.getPath() != null) {
                    newActivity.setPath(activity.getPath());
                }
                if (activity.getDescription() != null) {
                    newActivity.setDescription(activity.getDescription());
                }
                if (activity.getRules() != null) {
                    newActivity.setRules(activity.getRules());
                }
                activity = newActivity;
            }
            activityDao.save(activity);
            result.put("data", activity);
            result.put("message", "Data has been saved.");
        } catch (Exception ex) {
            activity = null;
            result.put("data", activity);
            result.put("message", "Data failed.");
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Change Status Active Activity
     *
     * @param activity
     * @return Activity
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/changeActive", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeActive(@ModelAttribute("activity") Activity activity) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            activity = activityDao.get(activity.getId());
            activity.setUpdateDate(new Date());
            activity.setActive(!activity.getActive());
            activityDao.save(activity);
            result.put("data", activity);
            result.put("message", "Data has been deleted.");
        } catch (Exception ex) {
            activity = null;
            result.put("data", activity);
            result.put("message", "Data failed.");
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Delete Activity
     *
     * @param activity
     * @return Activity
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@ModelAttribute("activity") Activity activity) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            activity = activityDao.get(activity.getId());
            activityDao.delete(activity);
            result.put("data", activity);
            result.put("message", "Data has been deleted.");
        } catch (Exception ex) {
            activity = null;
            result.put("data", activity);
            result.put("message", "Data failed.");
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
