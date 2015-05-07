package com.teslacode.workflow.controller;

import com.teslacode.workflow.dao.ModelDao;
import com.teslacode.workflow.model.Model;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Class Model Controller
 *
 * @version 1
 * @author Ade Fruandta
 */
@Controller
@RequestMapping("/model")
public class ModelController {
    
    @Autowired
    private ModelDao modelDao;

    /**
     * Default Constructor
     */
    public ModelController() {
    }
    
    /**
     * Get All List Model
     *
     * @param processId
     * @return List Model
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Model> get(@RequestParam(value = "processId") Integer processId) throws ParseException {
        List<Model> listModel = new ArrayList<>();
        try {
            listModel = modelDao.getByProcessId(processId);
        } catch (Exception ex) {
            Logger.getLogger(ModelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listModel;
    }
    
}
