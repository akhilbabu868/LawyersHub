/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.districtmasterDao;
import com.lawyershub.Model.Districtmaster;
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
public class districtmasterController {
    
     @Autowired
     districtmasterDao districtDAO;
    
    
    @RequestMapping("district.do")
    public ModelAndView dt() {
        ModelAndView dt = new ModelAndView("district");
        return dt;
    }

    
    @RequestMapping("savedistrict.do")
    public  @ResponseBody Map dtsave(Districtmaster objDistrict,@RequestParam(value="districtname")String name,
            @RequestParam(value = "id") Integer ID,@RequestParam(value="Status")Integer status){
    int flag=0;
    Map objMap = new HashMap();
        System.out.println("//ID"+ID);
        if (ID == 0) {
            objDistrict.setDistrict(name);
            objDistrict.setStatus(1);
            districtDAO.saveDistrictMaster(objDistrict);
            objMap.put("flag", 1);
            } else {
            objDistrict.setDistrict(name);
            objDistrict.setStatus(status);
            districtDAO.updateDistrict(objDistrict);
            objMap.put("flag", 2);
        }
             return objMap;
   
    }
    
     @RequestMapping("/editdistrictMaster.do")
    public @ResponseBody
    Districtmaster edit(@RequestParam("id") int id) {
        Districtmaster dt = districtDAO.getById(id);
        return dt;
    }

  
        
     //Function for Display State List   
     @RequestMapping("getDistrict.do")
     public @ResponseBody
     Map getProductList(
            @RequestParam("iDisplayStart") int iDisplayStart,
            @RequestParam("iDisplayLength") int iDisplayLength,
            @RequestParam("sSearch") String sSearch,
            @RequestParam("sEcho") String sEcho, HttpSession session) throws ParseException {
        Map objMap = new HashMap();
        try {
            List searchResult =districtDAO.getSearchdistrict(iDisplayStart, iDisplayLength, sSearch);
            Map mapCount =districtDAO.getSearchDistrictCount(sSearch);
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
     @RequestMapping("/setDistrictMasterStatus")
    public @ResponseBody
    int setMarketingMasterStatus(@RequestParam("id") int id,
            @RequestParam("status") int status,
            HttpSession session) {
        int flag = 0;
        try {
            Districtmaster objhisAdmType = districtDAO.getById(id);
            if (status == 1) {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_INACTIVE);
                districtDAO.updateStatus(objhisAdmType);
                flag = 1;
            } else {
                objhisAdmType.setId(id);
                objhisAdmType.setStatus(AppConstants.STATUS_ACTIVE);
                districtDAO.updateStatus(objhisAdmType);
                flag = 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    @RequestMapping(value = "districtList.do", method = RequestMethod.POST)
    @ResponseBody
    public List getDistrictList(HttpSession session) {
        System.out.println("hiiii");
        List districtList = districtDAO.getdistrict();
        System.out.println("//List" + districtList);
        return districtList;

    }
    
    
    
    
    
    
    
}
