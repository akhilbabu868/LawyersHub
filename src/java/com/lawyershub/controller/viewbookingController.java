/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.viewBookingDao;
import groovyjarjarcommonscli.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
public class viewbookingController {
   
    @Autowired
    viewBookingDao bookingDAO;
    
    @RequestMapping("viewbooking.do")
    public ModelAndView viewbooking() {
    ModelAndView obj = new ModelAndView("viewBooking");
    return obj;

    }
    
    
    //Function For Load Datat TABLE
     @RequestMapping("getBookingdetails.do")
     public @ResponseBody
     Map getUserdetailList(
            @RequestParam("iDisplayStart") int iDisplayStart,
            @RequestParam("iDisplayLength") int iDisplayLength,
            @RequestParam("sSearch") String sSearch,
            @RequestParam("lawyerID") Integer Id,
            @RequestParam("sEcho") String sEcho, HttpSession session) throws ParseException {
        Map objMap = new HashMap();
        try {
            List searchResult =bookingDAO.getSearchBooking(iDisplayStart, iDisplayLength, sSearch, Id);
//            Map mapCount =viewusrDAO.getSearchUserCount(sSearch);
            objMap.put("sEcho", Integer.parseInt(sEcho));
//            objMap.put("iTotalRecords", mapCount.get("CountBeforeFilter"));
//            objMap.put("iTotalDisplayRecords", mapCount.get("CountAfterFilter"));
            objMap.put("aaData", searchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objMap;
    
    }
    
    
}
