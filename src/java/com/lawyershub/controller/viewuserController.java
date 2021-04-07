/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.districtmasterDao;
import com.lawyershub.Dao.viewuserDao;
import com.lawyershub.Model.Districtmaster;
import com.lawyershub.Model.Login;
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
public class viewuserController {
    
     @Autowired
     viewuserDao viewusrDAO;
    
    @RequestMapping("viewuser.do")
    public ModelAndView showUser() {
        ModelAndView viewuser = new ModelAndView("viewuser");
        return viewuser;
    }
    
    
     @RequestMapping("getUserdetails.do")
     public @ResponseBody
     Map getUserdetailList(
            @RequestParam("iDisplayStart") int iDisplayStart,
            @RequestParam("iDisplayLength") int iDisplayLength,
            @RequestParam("sSearch") String sSearch,
            @RequestParam("sEcho") String sEcho, HttpSession session) throws ParseException {
        Map objMap = new HashMap();
        try {
            List searchResult =viewusrDAO.getSearchuser(iDisplayStart, iDisplayLength, sSearch);
            Map mapCount =viewusrDAO.getSearchUserCount(sSearch);
            objMap.put("sEcho", Integer.parseInt(sEcho));
            objMap.put("iTotalRecords", mapCount.get("CountBeforeFilter"));
            objMap.put("iTotalDisplayRecords", mapCount.get("CountAfterFilter"));
            objMap.put("aaData", searchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objMap;
    
    }
     
     
    @RequestMapping("/setUserStatus")
    public @ResponseBody
    int setuserloginStatus(@RequestParam("id") int id,
            @RequestParam("status") int status,
            HttpSession session) {
        
        System.out.println("//Status"+status);
        System.out.println("//id"+id);
        
        int flag = 0;
        try {
            Login objhisAdmType = viewusrDAO.getById(id);
            if (status == 1) {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_INACTIVE);
                viewusrDAO.updateStatus(objhisAdmType);
                flag = 1;
            } else {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_ACTIVE);
                viewusrDAO.updateStatus(objhisAdmType);
                flag = 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
     
     
    @RequestMapping("getdata.do")
    public @ResponseBody
    List getFullUserdetailList(
            @RequestParam("Viewid") int ID,
            HttpSession session) throws ParseException {
        List searchResult = null;
        try {
            searchResult = viewusrDAO.getUserDetails(ID);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;

    }
    
}
