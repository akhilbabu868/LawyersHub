/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.districtmasterDao;
import com.lawyershub.Dao.loginDao;
import com.lawyershub.Dao.packagemasterDao;
import com.lawyershub.Dao.userregistrationDao;
import com.lawyershub.Model.Login;
import com.lawyershub.Model.Userregistration;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
public class userregistrationController {
    
    
    @Autowired
	ServletContext context;
    @Autowired
     districtmasterDao districtDAO;
    
    @Autowired
     packagemasterDao pkgDAO;
    
    
     @Autowired
     userregistrationDao userDAO;
     
    @Autowired
    loginDao loginDAO;
    
    @RequestMapping("userregistration.do")
    public ModelAndView User() {
        ModelAndView user = new ModelAndView("UserRegistration");
        return user;
    }
        
    
    @RequestMapping(value = "ListTown.do", method = RequestMethod.POST)
    @ResponseBody
    public List getTownList(@RequestParam(value = "district") Integer DistrictID,HttpSession session) {
        System.out.println("hiiii");
        List townList = districtDAO.getTown(DistrictID);
        System.out.println("//List" + townList);
        return townList;

    }
    
    
     @RequestMapping(value = "pkgList.do", method = RequestMethod.POST)
    @ResponseBody
    public List getpackageList(HttpSession session) {
        System.out.println("hiiii");
        List pkgList = pkgDAO.getpackage();
        System.out.println("//List" + pkgList);
        return pkgList;

    }
    
    //Function For User Registration
    @RequestMapping("saveuser.do")
    public @ResponseBody
    int save(Userregistration Obj,Login ObjLogin,
            @RequestParam(value = "UserImage", required = false) MultipartFile file,
            @RequestParam(value = "district", required = false) Integer District,
            @RequestParam(value = "town", required = false) Integer Town,
            @RequestParam(value = "username", required = false) String Uname,
            SessionStatus status, HttpSession session) {
        try {
            Blob si = null;

            if (file != null) {

                si = Hibernate.createBlob(file.getInputStream());
                Obj.setImage(si);

            }
        } catch (IOException E) {
            //System.out.println(E);
        }
        Obj.setDistrictid(District);
        Obj.setTownid(Town);
        Obj.setCreateddate(new Date());
        Obj.setPackageid(1);
        ObjLogin.setUsername(Uname);
        ObjLogin.setRoleid(2);
        ObjLogin.setStatus(0);
        loginDAO.saveLogin(ObjLogin);
        userDAO.save(Obj);
        return 1;
    }
        
        
    //Function Load Img
      @RequestMapping(value = "getUserImg.do",method=RequestMethod.POST)
	public @ResponseBody List getHospitalLogo(@RequestParam(value = "email") String email) {
		Map mapp = new HashMap();
		List Det = userDAO.getUserImage(email);
		return Det;
	}
    
    
    
    
    
}
    
    
  
