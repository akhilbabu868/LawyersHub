/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.lawyerdashboardDao;
import com.lawyershub.Model.Lawyerregistration;
import com.lawyershub.Model.Userregistration;
import groovyjarjarcommonscli.ParseException;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
public class lawyerdashboardController {
   
    @Autowired
    lawyerdashboardDao lawyerDashboardDAO;
    
    
    
    @RequestMapping("Lawyerdashboard.do")
    public ModelAndView viewlawyerprofile() {
        ModelAndView obj = new ModelAndView("lawyerprofile");
        return obj;

    }
    
    
     @RequestMapping(value = "Lawyerprofile.do",method = RequestMethod.POST)
    @ResponseBody
    public List getLawyerprofileList(@RequestParam("user") String username ,HttpSession session) {
        System.out.println("hiiii");
        List profileList = lawyerDashboardDAO.getLawyerprofiledetails(username);
        Integer LawyerId = lawyerDashboardDAO.getRoleIDByUserID(username);
        System.out.println("//LawyerId" + LawyerId);
        System.out.println("//List" + profileList);
        session.setAttribute("lawyerID", LawyerId);
        return profileList;

    }
    
    @RequestMapping(value = "getLawyerImg.do", method = RequestMethod.POST)
    public @ResponseBody
    List getLawyerImage(@RequestParam(value = "email") String email) {
        Map mapp = new HashMap();
        List Det = lawyerDashboardDAO.getLawyerImage(email);
        return Det;
    }
    
    
     @RequestMapping("editlawyerprofiledata.do")
    public @ResponseBody
    List getEditUserdetailList(
            @RequestParam("user") String username,
            HttpSession session) throws ParseException {
        List searchResult = null;
        try {
            searchResult = lawyerDashboardDAO.getLawyerEditProfileDetails(username);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;

    }
    
    
    
    //Fucntion For Update
        @RequestMapping("updatelawyerprofileDetails.do")
    public @ResponseBody
    int UpdateProfile(
            @RequestParam("ID") int id,
            @RequestParam(value = "UserImage", required = false) MultipartFile file,
            @RequestParam(value = "address", required = false) String Address,
            @RequestParam(value = "mobile", required = false) String Mobile) {
            System.out.println("//Address"+Address);
            System.out.println("//Mobile"+Mobile );
        int flag = 0;
        try {
            Lawyerregistration Obj = lawyerDashboardDAO.getById(id);
            Blob si = null;

            if (file != null) {
                si = Hibernate.createBlob(file.getInputStream());
                Obj.setImage(si);
            }else{
                Obj.setImage(Obj.getImage());
            }
            
           if (Address != null) {
                Obj.setAddress(Address);
            }else{
                 Obj.setAddress(Obj.getAddress());
           } 
           
            if (Mobile != null) {
                Obj.setMobile(Mobile);
            }else{
                Obj.setMobile(Obj.getMobile());
            }
            
            lawyerDashboardDAO.update(Obj);
            flag = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
        
    //Function For Get Lawyer ID
    @RequestMapping(value = "getLawyerID.do", method = RequestMethod.POST)
    @ResponseBody
    public List getLawyerID(@RequestParam(value = "ID", required = true) Integer lawyerID) {
        System.out.println("hello" + lawyerID);
        List username = lawyerDashboardDAO.getLawyerId(lawyerID);
        System.out.println("//List" + lawyerID);
        return username;
    }
    
    
}
