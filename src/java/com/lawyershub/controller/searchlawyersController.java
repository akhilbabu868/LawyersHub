/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.userdashboardDao;
import com.lawyershub.Model.Rateing;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class searchlawyersController {
    
     @Autowired
     userdashboardDao userDashboardDAO;
    
    @RequestMapping("searchlawyer.do")
    public ModelAndView viewlawyerprofile() {
        ModelAndView obj = new ModelAndView("searchlawyers");
        return obj;

    } 
       //Show Full Lawyers
 
    @RequestMapping(value = "ShowLawyers.do",method = RequestMethod.POST)
    @ResponseBody
    public List getShowlawyerList(@RequestParam(value = "ID", required = false) Integer typeID) {
        System.out.println("hiiii"+typeID);
        List FullLawyerImg;
        if (typeID == null) {
            FullLawyerImg = userDashboardDAO.getFullLawyerImage();
        } else {
            FullLawyerImg = userDashboardDAO.getFullLawyerImageById(typeID);
        }
        System.out.println("//List" + FullLawyerImg);
        return FullLawyerImg;
    }
    
    
     @RequestMapping(value = "ShowLawyersById.do",method = RequestMethod.POST)
    @ResponseBody
    public List getShowlawyerListByID(@RequestParam(value = "type", required = true) Integer typeID) {
        System.out.println("hello"+typeID);
        List FullLawyerImg;
        if (typeID == null) {
            FullLawyerImg = userDashboardDAO.getFullLawyerImage();
        } else {
            FullLawyerImg = userDashboardDAO.getFullLawyerImageById(typeID);
        }
        System.out.println("//List" + FullLawyerImg);
        return FullLawyerImg;
    }
    
    
    @RequestMapping(value = "getUserID.do",method = RequestMethod.POST)
    @ResponseBody
    public List getUserID(@RequestParam(value = "email", required = true) String email) {
        System.out.println("hello"+email);
        List userID = userDashboardDAO.getUserId(email);
        System.out.println("//List" + userID);
        return userID;
    }
    
    
    //function for Add Review
     @RequestMapping("addreview.do")
    public  @ResponseBody Map Reviewsave(Rateing objRate,
            @RequestParam(value="rateing")Integer Rateing,
            @RequestParam(value = "review") String Review,
            @RequestParam(value="lawyerID")Integer lawyer,
            @RequestParam(value="userID")Integer user
             ){
        
         System.out.println("//Rateing"+Rateing);
         System.out.println("//Review"+Review);
         System.out.println("//lawyer"+lawyer);
         System.out.println("//user"+user);
        
        
            Map objMap = new HashMap();
       
            objRate.setRateing(Rateing);
            objRate.setComment(Review);
            objRate.setLawyerid(lawyer);
            objRate.setUserid(user);
            userDashboardDAO.saveRateing(objRate);
            objMap.put("flag", 1);
             return objMap;
   
    }
    
    

}
