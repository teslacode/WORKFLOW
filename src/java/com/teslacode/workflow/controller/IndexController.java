package com.teslacode.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class Index Controller
 * 
 * @version 1
 * @author Ade Fruandta
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    
    /**
     * Default Constructor
     */
    public IndexController(){
        
    }
    
    /**
     * Control load index
     * 
     * @param model
     * @return ModelAndView
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model)
    {
        return model;
    }
    
}
