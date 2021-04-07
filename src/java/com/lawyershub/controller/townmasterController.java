/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;


import com.lawyershub.Dao.townmasterDao;
import com.lawyershub.Model.Townmaster;
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
public class townmasterController {
    
    
     @Autowired
     townmasterDao townDao;
    
     @RequestMapping("town.do")
    public ModelAndView mv() {
        ModelAndView obj = new ModelAndView("townmaster");
        return obj;

    }
    
        @RequestMapping("savetown.do")
    public @ResponseBody
    Map pakagesave(Townmaster objTown, @RequestParam(value = "Town") String name, 
            @RequestParam(value = "District") Integer districtID,
            @RequestParam(value = "id") Integer ID,@RequestParam(value = "Status") Integer status) {
        int flag = 0;
        Map objMap = new HashMap();
        System.out.println("//ID" + ID);
        if (ID == 0) {
            objTown.setTown(name);
            objTown.setDistrictid(districtID);
            objTown.setStatus(1);
            townDao.saveTownmasterMaster(objTown);
            objMap.put("flag", 1);
        } else {
            objTown.setTown(name);
            objTown.setDistrictid(districtID);
            objTown.setStatus(status);
            townDao.updateTownmaster(objTown);
            objMap.put("flag", 2);
        }
        return objMap;
    }
    
    
    //function for Display
     @RequestMapping("getTown.do")
     public @ResponseBody
     Map getProductList(
            @RequestParam("iDisplayStart") int iDisplayStart,
            @RequestParam("iDisplayLength") int iDisplayLength,
            @RequestParam("sSearch") String sSearch,
            @RequestParam("sEcho") String sEcho, HttpSession session) throws ParseException {
        Map objMap = new HashMap();
        try {
            List searchResult =townDao.getSearchTown(iDisplayStart, iDisplayLength, sSearch);
            Map mapCount =townDao.getSearchTownCount(sSearch);
            objMap.put("sEcho", Integer.parseInt(sEcho));
            objMap.put("iTotalRecords", mapCount.get("CountBeforeFilter"));
            objMap.put("iTotalDisplayRecords", mapCount.get("CountAfterFilter"));
            objMap.put("aaData", searchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objMap;
    
    }
     
    @RequestMapping("/edittownMaster.do")
    public @ResponseBody
    Townmaster edit(@RequestParam("id") int id) {
        Townmaster dt = townDao.getById(id);
        return dt;
    }
    
    
      //Function for change Status
     @RequestMapping("/setTownMasterStatus")
    public @ResponseBody
    int setMarketingMasterStatus(@RequestParam("id") int id,
            @RequestParam("status") int status,
            HttpSession session) {
        int flag = 0;
        try {
            Townmaster objhisAdmType = townDao.getById(id);
            if (status == 1) {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_INACTIVE);
                townDao.updateStatus(objhisAdmType);
                flag = 1;
            } else {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_ACTIVE);
                townDao.updateStatus(objhisAdmType);
                flag = 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    
    
    
    
    
    
    
    
    
}
