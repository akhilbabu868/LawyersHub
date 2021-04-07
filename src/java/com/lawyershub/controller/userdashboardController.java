/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.userdashboardDao;
import com.lawyershub.Dao.userregistrationDao;
import com.lawyershub.Model.Userregistration;
import groovyjarjarcommonscli.ParseException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ASUS
 */
@Controller
public class userdashboardController {
    
    
     @Autowired
    userdashboardDao userDashboardDAO;
     
     @Autowired
     userregistrationDao userDAO;
    
     @RequestMapping("Userdashboard.do")
    public ModelAndView viewuserprofile() {
        ModelAndView obj = new ModelAndView("userprofile");
        return obj;

    }
    
     @RequestMapping(value = "Userprofile.do",method = RequestMethod.POST)
    @ResponseBody
    public List getUserprofileList(@RequestParam("user") String username ,HttpSession session) {
        System.out.println("username"+username);
        List profileList = userDashboardDAO.getUserprofiledetails(username);
        System.out.println("//List" + profileList);
        return profileList;

    }
    
     //Function Edit
     @RequestMapping("editprofiledata.do")
    public @ResponseBody
    List getEditUserdetailList(
            @RequestParam("user") String username,
            HttpSession session) throws ParseException {
        List searchResult = null;
        try {
            searchResult = userDAO.getEditProfileDetails(username);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;

    }
    
    
   
    @RequestMapping("updateprofileDetails.do")
	public @ResponseBody
	int UpdateProfile(
                @RequestParam("ID") int id,
                @RequestParam(value = "UserImage",required = false) MultipartFile file,
                @RequestParam(value = "address",required = false) String Address,
                @RequestParam(value = "mobile",required = false) String Mobile) {
        
		int flag = 0;
		try {
			Userregistration Obj = userDAO.getById(id);
                        Blob si = null;

                    if (file != null) {
                        si = Hibernate.createBlob(file.getInputStream());
                        Obj.setImage(si);
                    }else{
                        Obj.setImage(Obj.getImage());
                    }
                    if(Address != null){
                        Obj.setAddress(Address);
                    }else{
                        Obj.setAddress(Obj.getAddress());
                    }
                    if(Mobile !=null){
                        Obj.setMobile(Mobile);
                    }else{
                        Obj.setMobile(Obj.getMobile());
                    }
                    userDAO.update(Obj);
	            flag = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
    
}
