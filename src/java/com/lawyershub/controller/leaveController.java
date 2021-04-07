/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.leaveDao;
import com.lawyershub.Model.Leaves;
import com.lawyershub.Model.Townmaster;
import groovyjarjarcommonscli.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
public class leaveController {
    
    @Autowired
    leaveDao Leave;
    
    
    
    @RequestMapping("leaves.do")
    public ModelAndView mv() {
        ModelAndView obj = new ModelAndView("AddLeaves");
        return obj;

    }
    
    
    //Save Leaves
    @RequestMapping("saveleaves.do")
    public @ResponseBody
    Map saveleaves(Leaves objLeave, 
            @RequestParam(value = "fromDate") String FromDate, 
            @RequestParam(value = "toDate") String ToDate,
            @RequestParam(value = "Uname") String Username,
            @RequestParam(value = "id") Integer ID) throws java.text.ParseException {
        int flag = 0;
        Map objMap = new HashMap();
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date Frmdate = sdf.parse(FromDate);
        Date todate = sdf.parse(ToDate);
        System.out.println("Frmdate"+Frmdate); //Prints 26/10/2015
        System.out.println("todate"+todate); //Prints 26/10/2015

       
        System.out.println("//ID" + ID);
        if (ID == 0) {
               objLeave.setFromdate(Frmdate);
               objLeave.setTodate(todate);
               objLeave.setLawyerusername(Username);
               Leave.saveLeave(objLeave);
               objMap.put("flag", 1);
        } else{
            
            objLeave.setFromdate(Frmdate);
            objLeave.setTodate(todate);
            objLeave.setLawyerusername(Username);
            Leave.updateLeave(objLeave);
            objMap.put("flag", 2);
        }
        return objMap;
    }
    
    
    @RequestMapping("/editleaves.do")
    public @ResponseBody
    Leaves edit(@RequestParam("id") int id) {
        Leaves Leaves = Leave.getById(id);
        return Leaves;
    }
    @RequestMapping(value = "deleteleaves.do", method = RequestMethod.POST)
    public @ResponseBody
    int deletleaves(@RequestParam("id") int ID) {
        Leave.delete(ID);
        return 1;
    }


    
    
    //Function Data  Table
    @RequestMapping("getleaves.do")
    public @ResponseBody
    Map getLeaveList(
            @RequestParam("iDisplayStart") int iDisplayStart,
            @RequestParam("iDisplayLength") int iDisplayLength,
            @RequestParam("username") String Username,
            @RequestParam("sEcho") String sEcho, HttpSession session) throws ParseException {
        Map objMap = new HashMap();
        try {
            List searchResult = Leave.getLeaves(iDisplayStart, iDisplayLength,Username);
            Map mapCount = Leave.getLeavesCount(Username);
            objMap.put("iTotalRecords", mapCount.get("CountBeforeFilter"));
            objMap.put("iTotalDisplayRecords", mapCount.get("CountAfterFilter"));
            objMap.put("aaData", searchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objMap;

    }
    
    
    
}



