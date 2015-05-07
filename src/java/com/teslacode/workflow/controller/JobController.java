package com.teslacode.workflow.controller;

import com.teslacode.workflow.dao.JobDao;
import com.teslacode.workflow.model.Job;
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
 * Class Job Controller
 *
 * @version 1
 * @author Ade Fruandta
 */
@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobDao jobDao;

    /**
     * Default Constructor
     */
    public JobController() {

    }

    /**
     * Get All List Job
     *
     * @return List Job
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Job> get() throws ParseException {
        List<Job> listJob = new ArrayList<>();
        try {
            listJob = jobDao.get();
        } catch (Exception ex) {
            Logger.getLogger(JobController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listJob;
    }
    
    /**
     * Get Job By Job Code
     *
     * @param code (Job Code)
     * @return List Job
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/getByCode", method = RequestMethod.GET)
    @ResponseBody
    public Job get(@RequestParam(value = "code") String code) throws ParseException {
        Job job = new Job();
        try {
            job = jobDao.get(code);
        } catch (Exception ex) {
            Logger.getLogger(JobController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return job;
    }

    /**
     * Save Job
     *
     * @param job
     * @return Job
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(@ModelAttribute("job") Job job) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            jobDao.save(job);
            result.put("data", job);
            result.put("message", "Data has been saved.");
        } catch (Exception ex) {
            job = null;
            result.put("data", job);
            result.put("message", "Data failed.");
            Logger.getLogger(JobController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * Change Status Active Job
     *
     * @param job
     * @return Job
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/changeActive", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeActive(@ModelAttribute("job") Job job) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            job = jobDao.get(job.getCode());
            job.setUpdateDate(new Date());
            job.setActive(!job.getActive());
            jobDao.save(job);
            result.put("data", job);
            result.put("message", "Data has been deleted.");
        } catch (Exception ex) {
            job = null;
            result.put("data", job);
            result.put("message", "Data failed.");
            Logger.getLogger(JobController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * Delete Job
     *
     * @param job
     * @return Job
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@ModelAttribute("job") Job job) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            job = jobDao.get(job.getCode());
            jobDao.delete(job);
            result.put("data", job);
            result.put("message", "Data has been deleted.");
        } catch (Exception ex) {
            job = null;
            result.put("data", job);
            result.put("message", "Data failed.");
            Logger.getLogger(JobController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
