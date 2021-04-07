/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.viewlawyerDao;
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
public class viewlawyerController {
    
    @Autowired
     viewlawyerDao viewlawyerDAO;
    
    @RequestMapping("viewlawyer.do")
    public ModelAndView showLawyer() {
        ModelAndView viewlawyer = new ModelAndView("viewlawyer");
        return viewlawyer;
    }
    
     @RequestMapping("getLawyerdetails.do")
     public @ResponseBody
     Map getUserdetailList(
            @RequestParam("iDisplayStart") int iDisplayStart,
            @RequestParam("iDisplayLength") int iDisplayLength,
            @RequestParam("sSearch") String sSearch,
            @RequestParam("sEcho") String sEcho, HttpSession session) throws ParseException {
        Map objMap = new HashMap();
        try {
            List searchResult =viewlawyerDAO.getSearchlawyers(iDisplayStart, iDisplayLength, sSearch);
            Map mapCount =viewlawyerDAO.getSearchLawyerCount(sSearch);
            objMap.put("sEcho", Integer.parseInt(sEcho));
            objMap.put("iTotalRecords", mapCount.get("CountBeforeFilter"));
            objMap.put("iTotalDisplayRecords", mapCount.get("CountAfterFilter"));
            objMap.put("aaData", searchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objMap;
    
    }
     
    @RequestMapping("getLawyerdata.do")
    public @ResponseBody
    List getFullUserdetailList(
            @RequestParam("Viewid") int ID,
            HttpSession session) throws ParseException {
        List searchResult = null;
        try {
            searchResult = viewlawyerDAO.getLawyerDetails(ID);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;
        
    }
     
     
     
    
}
