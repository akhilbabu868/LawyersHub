/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.lawyermasterDao;
import com.lawyershub.Model.Lawyertype;
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
public class lawyertypemasterController {
    
    
      @Autowired
      lawyermasterDao lawyertypeDAO;
    
    @RequestMapping("lawyertype.do")
    public ModelAndView viewlawyertype() {
        ModelAndView obj = new ModelAndView("lawyertypemaster");
        return obj;

    }
    
    @RequestMapping("savelawyertype.do")
    public @ResponseBody
    Map savelawyertype(Lawyertype objlawyertype, @RequestParam(value = "lawyer") String name,
            @RequestParam(value = "Description") String desc,
            @RequestParam(value = "id") Integer ID,@RequestParam(value = "Status") Integer status) {
        Map objMap = new HashMap();
        System.out.println("//ID" + ID);
        if (ID == 0) {
            objlawyertype.setLawyertype(name);
            objlawyertype.setDescription(desc);
            objlawyertype.setStatus(1);
            lawyertypeDAO.saveLawyertypeMaster(objlawyertype);
            objMap.put("flag", 1);
        } else {
            objlawyertype.setLawyertype(name);
            objlawyertype.setDescription(desc);
            objlawyertype.setStatus(status);
            lawyertypeDAO.updateLawyertype(objlawyertype);
            objMap.put("flag", 2);
        }
        return objMap;
    }
    
    @RequestMapping("/editlawyertypeMaster.do")
    public @ResponseBody
    Lawyertype edit(@RequestParam("id") int id) {
        Lawyertype lawyertype = lawyertypeDAO.getById(id);
        return lawyertype;
    }
    
    
    @RequestMapping("getlawyertype.do")
    public @ResponseBody
    Map getLawyertypeList(
            @RequestParam("iDisplayStart") int iDisplayStart,
            @RequestParam("iDisplayLength") int iDisplayLength,
            @RequestParam("sSearch") String sSearch,
            @RequestParam("sEcho") String sEcho, HttpSession session) throws ParseException {
        Map objMap = new HashMap();
        try {
            List searchResult = lawyertypeDAO.getSearchlawyertype(iDisplayStart, iDisplayLength, sSearch);
            Map mapCount = lawyertypeDAO.getSearchLawyertypeCount(sSearch);
            objMap.put("sEcho", Integer.parseInt(sEcho));
            objMap.put("iTotalRecords", mapCount.get("CountBeforeFilter"));
            objMap.put("iTotalDisplayRecords", mapCount.get("CountAfterFilter"));
            objMap.put("aaData", searchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objMap;

    }
    
    @RequestMapping("/setLawyertypeMasterStatus")
    public @ResponseBody
    int setMarketingMasterStatus(@RequestParam("id") int id,
            @RequestParam("status") int status,
            HttpSession session) {
        int flag = 0;
        try {
            Lawyertype objhisAdmType = lawyertypeDAO.getById(id);
            if (status == 1) {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_INACTIVE);
                lawyertypeDAO.updateStatus(objhisAdmType);
                flag = 1;
            } else {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_ACTIVE);
                lawyertypeDAO.updateStatus(objhisAdmType);
                flag = 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
