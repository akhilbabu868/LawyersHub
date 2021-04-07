/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.qualificationmasterDao;
import com.lawyershub.Model.Qualificationmaster;
import groovyjarjarcommonscli.ParseException;
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
public class qualificationmasterController {
    
     @Autowired
     qualificationmasterDao qualificationDAO;
     
    
    @RequestMapping("qualificationmaster.do")
    public ModelAndView qualification() {
        ModelAndView qualification = new ModelAndView("qualificationmaster");
        return qualification;
    }
    
    
    
    @RequestMapping("savequalification.do")
    public  @ResponseBody Map savequalification(Qualificationmaster objqualification,@RequestParam(value="qualification")String name,
            @RequestParam(value = "id") Integer ID,@RequestParam(value = "Status") Integer status){
    int flag=0;
    Map objMap = new HashMap();
        System.out.println("//ID"+ID);
        if (ID == 0) {
            objqualification.setQualification(name);
            objqualification.setStatus(1);
            qualificationDAO.saveQualificationMaster(objqualification);
            objMap.put("flag", 1);
            } else {
            objqualification.setQualification(name);
            objqualification.setStatus(status);
            qualificationDAO.updateQualification(objqualification);
            objMap.put("flag", 2);
        }
             return objMap;
   
    }
    
     @RequestMapping("/editqualificationMaster.do")
    public @ResponseBody
    Qualificationmaster edit(@RequestParam("id") int id) {
        Qualificationmaster qm = qualificationDAO.getById(id);
        return qm;
    }
    
    
    @RequestMapping("getQualification.do")
    public @ResponseBody
    Map getProductList(
            @RequestParam("iDisplayStart") int iDisplayStart,
            @RequestParam("iDisplayLength") int iDisplayLength,
            @RequestParam("sSearch") String sSearch,
            @RequestParam("sEcho") String sEcho, HttpSession session) throws ParseException {
        Map objMap = new HashMap();
        try {
            List searchResult = qualificationDAO.getSearchqulification(iDisplayStart, iDisplayLength, sSearch);
            Map mapCount = qualificationDAO.getSearchqulificationCount(sSearch);
            objMap.put("sEcho", Integer.parseInt(sEcho));
            objMap.put("iTotalRecords", mapCount.get("CountBeforeFilter"));
            objMap.put("iTotalDisplayRecords", mapCount.get("CountAfterFilter"));
            objMap.put("aaData", searchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objMap;

    }

      @RequestMapping("/setQualificationMasterStatus.do")
    public @ResponseBody
    int setMarketingMasterStatus(@RequestParam("id") int id,
            @RequestParam("status") int status,
            HttpSession session) {
        int flag = 0;
        try {
            Qualificationmaster objhisAdmType = qualificationDAO.getById(id);
            if (status == 1) {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_INACTIVE);
                qualificationDAO.updateStatus(objhisAdmType);
                flag = 1;
            } else {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_ACTIVE);
                qualificationDAO.updateStatus(objhisAdmType);
                flag = 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    
    @RequestMapping(value = "qualificationList.do", method = RequestMethod.POST)
    @ResponseBody
    public List getqualificationList(HttpSession session) {
        List qualificationList = qualificationDAO.getQualification();
        System.out.println("//List" + qualificationList);
        return qualificationList;

    }
    
    
    
    
    
    
}
