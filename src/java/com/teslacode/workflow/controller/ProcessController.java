package com.teslacode.workflow.controller;

import com.teslacode.workflow.dao.ActivityDao;
import com.teslacode.workflow.dao.ModelDao;
import com.teslacode.workflow.dao.NextFlagDao;
import com.teslacode.workflow.dao.ProcessDao;
import com.teslacode.workflow.model.Activity;
import com.teslacode.workflow.model.Model;
import com.teslacode.workflow.model.NextFlag;
import com.teslacode.workflow.model.Process;
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
 * Class Process Controller
 *
 * @version 1
 * @author Ade Fruandta
 */
@Controller
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessDao processDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private NextFlagDao nextFlagDao;
    @Autowired
    private ModelDao modelDao;

    /**
     * Default Constructor
     */
    public ProcessController() {

    }

    /**
     * Get All List Process
     *
     * @return List Process
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Process> get() throws ParseException {
        List<Process> listProcess = new ArrayList<>();
        try {
            listProcess = processDao.get();
        } catch (Exception ex) {
            Logger.getLogger(ProcessController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProcess;
    }

    /**
     * Get Process by Job Code
     *
     * @param jobCode (Job Code)
     * @return List Process
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, params = {"jobCode"})
    @ResponseBody
    public List<Process> get(@RequestParam(value = "jobCode") String jobCode) throws ParseException {
        List<Process> listProcess = new ArrayList<>();
        try {
            listProcess = processDao.get(jobCode);
        } catch (Exception ex) {
            Logger.getLogger(ProcessController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProcess;
    }

    /**
     * Save Process
     *
     * @param process
     * @return Process
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(@ModelAttribute("process") Process process) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            // if id exists update
            if (process.getId() != null) {
                Process newProcess = processDao.get(process.getId());
                newProcess.setUpdateDate(new Date());
                newProcess.setNotes(process.getNotes());
                process = newProcess;
            } else { //if id null nex process
                Process lastProcess = processDao.getLastVersion(process.getJobCode());
                if (lastProcess != null) {
                    process.setVersion(lastProcess.getVersion() + 1);
                } else {
                    process.setVersion(1);
                }
            }

            processDao.save(process);
            result.put("data", process);
            result.put("message", "Data has been saved.");
        } catch (Exception ex) {
            process = null;
            result.put("data", process);
            result.put("message", "Data failed.");
            Logger.getLogger(ProcessController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Change Status Active Process
     *
     * @param process
     * @return Process
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/changeActive", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeActive(@ModelAttribute("process") Process process) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            // Deactivate Active Process
            Process oldProcess = processDao.getActiveVersion(process.getJobCode());
            if (oldProcess != null) {
                oldProcess.setUpdateDate(new Date());
                oldProcess.setActive(Boolean.FALSE);
                processDao.save(oldProcess);
            }

            // Activate Deactive Process
            process = processDao.get(process.getId());
            process.setUpdateDate(new Date());
            process.setActive(!process.getActive());
            processDao.save(process);
            result.put("data", process);
            result.put("message", "Data has been deleted.");
        } catch (Exception ex) {
            process = null;
            result.put("data", process);
            result.put("message", "Data failed.");
            Logger.getLogger(ProcessController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Save Process
     *
     * @param process
     * @return Process
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/duplicate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> duplicate(@ModelAttribute("process") Process process) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            // Duplicate Process
            // Set New Process
            Process newProcess = processDao.get(process.getId());
            newProcess.setId(null);
            newProcess.setActive(Boolean.FALSE);

            // Set New Version
            Process lastProcess = processDao.getLastVersion(process.getJobCode());
            newProcess.setVersion(lastProcess.getVersion() + 1);

            // Save Process
            processDao.save(newProcess);
            newProcess = processDao.getLastVersion(process.getJobCode());

            // Duplicate Model
            List<Model> listModel = modelDao.getByProcessId(process.getId());
            for (Model model : listModel) {
                // Set New Model
                Model newModel = model;
                newModel.setId(null);
                newModel.setProcessId(newProcess.getId());

                // Save Model
                modelDao.save(model);
            }
            
            // Duplicate Activity
            List<Activity> listActivity = activityDao.getByProcessId(process.getId());
            for (Activity activity : listActivity) {
                // Set New Activity
                Activity newActivity = activity;
                newActivity.setId(null);
                newActivity.setProcessId(newProcess.getId());

                // Save Activity
                activityDao.save(activity);
            }

            // Duplicate Next Flag
            List<NextFlag> listNextFlag = nextFlagDao.getByProcessId(process.getId());
            for (NextFlag nextFlag : listNextFlag) {
                // Set new Next Flag
                NextFlag newNextFlag = nextFlag;
                newNextFlag.setId(null);

                // Get Similar Activity
                Activity fromActivity = activityDao.get(newNextFlag.getFromActivityId());
                Activity toActivity = activityDao.get(newNextFlag.getToActivityId());
                fromActivity.setProcessId(newProcess.getId());
                toActivity.setProcessId(newProcess.getId());

                // Set New Similar Activity
                Activity newFromActivity = activityDao.getSimilar(fromActivity);
                Activity newToActivity = activityDao.getSimilar(toActivity);

                // Set From To Activity for New Next Flag
                newNextFlag.setFromActivityId(newFromActivity.getId());
                newNextFlag.setToActivityId(newToActivity.getId());
                nextFlagDao.save(newNextFlag);
            }

            result.put("data", process);
            result.put("message", "Data has been saved.");
        } catch (Exception ex) {
            process = null;
            result.put("data", process);
            result.put("message", "Data failed.");
            Logger.getLogger(ProcessController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Delete Process
     *
     * @param process
     * @return Process
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@ModelAttribute("process") Process process) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            process = processDao.get(process.getId());
            processDao.delete(process);
            result.put("data", process);
            result.put("message", "Data has been deleted.");
        } catch (Exception ex) {
            process = null;
            result.put("data", process);
            result.put("message", "Data failed.");
            Logger.getLogger(ProcessController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
