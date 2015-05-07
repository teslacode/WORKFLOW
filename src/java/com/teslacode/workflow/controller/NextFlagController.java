package com.teslacode.workflow.controller;

import com.teslacode.workflow.dao.NextFlagDao;
import com.teslacode.workflow.model.NextFlag;
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
 * Class NextFlag Controller
 *
 * @version 1
 * @author Ade Fruandta
 */
@Controller
@RequestMapping("/nextflag")
public class NextFlagController {
    
    @Autowired
    private NextFlagDao nextFlagDao;

    /**
     * Default Constructor
     */
    public NextFlagController() {

    }
    
    /**
     * Get All List NextFlag
     *
     * @return List NextFlag
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<NextFlag> get() throws ParseException {
        List<NextFlag> listNextFlag = new ArrayList<>();
        try {
            listNextFlag = nextFlagDao.get();
        } catch (Exception ex) {
            Logger.getLogger(NextFlagController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNextFlag;
    }
    
    /**
     * Get NextFlag by Process Id
     *
     * @param processId (Process Id)
     * @return List NextFlag
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, params = {"processId"})
    @ResponseBody
    public List<NextFlag> get(@RequestParam(value = "processId", required = true) Integer processId) throws ParseException {
        List<NextFlag> listNextFlag = new ArrayList<>();
        try {
            listNextFlag = nextFlagDao.getByProcessId(processId);
        } catch (Exception ex) {
            Logger.getLogger(NextFlagController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNextFlag;
    }
    
    /**
     * Save NextFlag
     *
     * @param nextFlag
     * @return NextFlag
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(@ModelAttribute("nextFlag") NextFlag nextFlag) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            if (nextFlag.getId() != null) {
                NextFlag newNextFlag = nextFlagDao.get(nextFlag.getId());
                newNextFlag.setUpdateDate(new Date());
                newNextFlag.setFromActivityId(nextFlag.getFromActivityId());
                newNextFlag.setToActivityId(nextFlag.getToActivityId());
                newNextFlag.setFlag(nextFlag.getFlag());
                newNextFlag.setOrder(nextFlag.getOrder());
                newNextFlag.setHide(nextFlag.getHide());
                nextFlag = newNextFlag;
            } 
            nextFlagDao.save(nextFlag);
            result.put("data", nextFlag);
            result.put("message", "Data has been saved.");
        } catch (Exception ex) {
            nextFlag = null;
            result.put("data", nextFlag);
            result.put("message", "Data failed.");
            Logger.getLogger(NextFlagController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * Change Status Active NextFlag
     *
     * @param nextFlag
     * @return NextFlag
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/changeActive", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeActive(@ModelAttribute("nextFlag") NextFlag nextFlag) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            nextFlag = nextFlagDao.get(nextFlag.getId());
            nextFlag.setUpdateDate(new Date());
            nextFlag.setActive(!nextFlag.getActive());
            nextFlagDao.save(nextFlag);
            result.put("data", nextFlag);
            result.put("message", "Data has been deleted.");
        } catch (Exception ex) {
            nextFlag = null;
            result.put("data", nextFlag);
            result.put("message", "Data failed.");
            Logger.getLogger(NextFlagController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * Delete NextFlag
     *
     * @param nextFlag
     * @return NextFlag
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@ModelAttribute("nextFlag") NextFlag nextFlag) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        try {
            nextFlag = nextFlagDao.get(nextFlag.getId());
            nextFlagDao.delete(nextFlag);
            result.put("data", nextFlag);
            result.put("message", "Data has been deleted.");
        } catch (Exception ex) {
            nextFlag = null;
            result.put("data", nextFlag);
            result.put("message", "Data failed.");
            Logger.getLogger(NextFlagController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
