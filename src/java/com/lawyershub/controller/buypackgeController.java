/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.buypackageDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
public class buypackgeController {
    
    @Autowired
     buypackageDao buypkgDAO;
    
     @RequestMapping("buypkg.do")
     public ModelAndView buypkg() {
        ModelAndView obj = new ModelAndView("buypackage");
        return obj;

    }
     
     //Show Full pkg
    @RequestMapping(value = "ShowfullPkg.do", method = RequestMethod.POST)
    @ResponseBody
    public List getShowPkgList() {
        System.out.println("hiiii");
        List FullLpkgdetails = buypkgDAO.getFullpkgDetails();
        System.out.println("//List" + FullLpkgdetails);
        return FullLpkgdetails;
    }
}
