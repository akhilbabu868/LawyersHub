/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.AdminDashboardDao;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ASUS
 */
@Controller
public class AdminDashboardController {
    
    
    @Autowired
    AdminDashboardDao AdminDAO;
    
    
    @RequestMapping(value = "getUserWiseChart.do", method = RequestMethod.POST)
    @ResponseBody
    public Map getUserWiseChart() throws ParseException {
        List Totalvisit = null;
        Map objMap = new HashMap();
        Totalvisit = AdminDAO.getAllUserRegistrationReport();
        objMap.put("data", Totalvisit);
        return objMap;
    }
    
    
       @RequestMapping(value = "getLawyerWiseChart.do", method = RequestMethod.POST)
    @ResponseBody
    public Map getLawyerWiseChart() throws ParseException {
        List Totalvisit = null;
        Map objMap = new HashMap();
        Totalvisit = AdminDAO.getAllLawyerRegistrationReport();
        objMap.put("data", Totalvisit);
        return objMap;
    }
    
}
