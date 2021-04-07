/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.lawyermasterDao;
import com.lawyershub.Dao.lawyerregistrationDao;
import com.lawyershub.Dao.loginDao;
import com.lawyershub.Model.Lawyerregistration;
import com.lawyershub.Model.Login;
import com.lawyershub.Model.Userregistration;
import java.io.IOException;
import java.sql.Blob;
import java.util.Date;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */

@Controller
public class lawyerregistrationController {
    
    @Autowired
    lawyermasterDao lawyertypeDAO;
    
    @Autowired
    loginDao loginDAO;
    
    @Autowired
    lawyerregistrationDao lawyerregDAO;
    
    @RequestMapping("lawyer.do")
    public ModelAndView viewlawyerregistration() {
        ModelAndView obj = new ModelAndView("Lawyerregistration");
        return obj;

    }
    
    
    @RequestMapping(value = "lawyertypeList.do", method = RequestMethod.POST)
    @ResponseBody
    public List getlawyertypeList(HttpSession session) {
        List lawyertypeList = lawyertypeDAO.getlawyertype();
        System.out.println("//List" + lawyertypeList);
        return lawyertypeList;

    }
    
    
        //Function For Lawyer Registration
    @RequestMapping("Lawyersave.do")
    public @ResponseBody
    int save(Lawyerregistration Obj,Login ObjLogin,
            @RequestParam(value = "LawyerImage", required = false) MultipartFile file,
            @RequestParam(value = "district", required = false) Integer District,
            @RequestParam(value = "qualification", required = false) Integer Qualification,
            @RequestParam(value = "lawyertype", required = false) Integer Lawyertype,
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
        Obj.setQualificationid(Qualification);
        Obj.setLawyertypeid(Lawyertype);
        Obj.setCreateddate(new Date());
        ObjLogin.setUsername(Uname);
        ObjLogin.setRoleid(3);
        ObjLogin.setStatus(0);
        loginDAO.saveLogin(ObjLogin);
        lawyerregDAO.save(Obj);
        return 1;
    }
      
  
}
