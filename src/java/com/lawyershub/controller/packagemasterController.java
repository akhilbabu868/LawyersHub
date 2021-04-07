/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.packagemasterDao;
import com.lawyershub.Model.Districtmaster;
import com.lawyershub.Model.Packagemaster;
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
public class packagemasterController {
    
     @Autowired
     packagemasterDao pkgDAO;
    
    
    @RequestMapping("package.do")
    public ModelAndView mv() {
        ModelAndView mv = new ModelAndView("pakagemaster");
        return mv;

    }
    
    @RequestMapping("savepakage.do")
    public @ResponseBody
    Map pakagesave(Packagemaster objPkg, @RequestParam(value = "packagename") String name, 
            @RequestParam(value = "description") String Description,
            @RequestParam(value = "rate") Double Rate, @RequestParam(value = "id") Integer ID,@RequestParam(value = "Status") Integer status) {
        int flag = 0;
        Map objMap = new HashMap();
        System.out.println("//ID" + ID);
        if (ID == 0) {
            objPkg.setPackagename(name);
            objPkg.setDescription(Description);
            objPkg.setRate(Rate);
            objPkg.setStatus(1);
            pkgDAO.savePackageMaster(objPkg);
            objMap.put("flag", 1);
        } else {
            objPkg.setPackagename(name);
            objPkg.setDescription(Description);
            objPkg.setRate(Rate);
            objPkg.setStatus(status);
            pkgDAO.updatePackage(objPkg);
            objMap.put("flag", 2);
        }
        return objMap;
    }
    
     @RequestMapping("/editPackageMaster.do")
    public @ResponseBody
    Packagemaster edit(@RequestParam("id") int id) {
        Packagemaster pkg = pkgDAO.getById(id);
        return pkg;
    }
    
       //Function for Display State List   
     @RequestMapping("getPackage.do")
     public @ResponseBody
     Map getProductList(
            @RequestParam("iDisplayStart") int iDisplayStart,
            @RequestParam("iDisplayLength") int iDisplayLength,
            @RequestParam("sSearch") String sSearch,
            @RequestParam("sEcho") String sEcho, HttpSession session) throws ParseException {
        Map objMap = new HashMap();
        try {
            List searchResult =pkgDAO.getSearchpackage(iDisplayStart, iDisplayLength, sSearch);
            Map mapCount =pkgDAO.getSearchpackageCount(sSearch);
            objMap.put("sEcho", Integer.parseInt(sEcho));
            objMap.put("iTotalRecords", mapCount.get("CountBeforeFilter"));
            objMap.put("iTotalDisplayRecords", mapCount.get("CountAfterFilter"));
            objMap.put("aaData", searchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objMap;
    
    }
     
          
     //Function for change Status
     @RequestMapping("/setpackageMasterStatus")
    public @ResponseBody
    int setMarketingMasterStatus(@RequestParam("id") int id,
            @RequestParam("status") int status,
            HttpSession session) {
        int flag = 0;
        try {
            Packagemaster objhisAdmType = pkgDAO.getById(id);
            if (status == 1) {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_INACTIVE);
                pkgDAO.updateStatus(objhisAdmType);
                flag = 1;
            } else {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_ACTIVE);
                pkgDAO.updateStatus(objhisAdmType);
                flag = 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
