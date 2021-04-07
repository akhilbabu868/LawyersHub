/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.loginDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
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
public class loginController {
    
   @Autowired
   private  loginDao logDAO;
   
   
    @RequestMapping("start.do")
    public ModelAndView mv() {
        ModelAndView obj = new ModelAndView("index");
        return obj;

    }
    
     @RequestMapping("login.do")
      public ModelAndView login() {
        ModelAndView obj = new ModelAndView("login");
        return obj;

    }
      
//  =====================Function For Checking Login Authentication================================     
    @RequestMapping(value = "auth.do",method = RequestMethod.POST)
    @ResponseBody
    public List Log(@RequestParam(value = "username") String Username,
                    @RequestParam(value = "password") String Password,HttpSession session) {
        List LogInfo;
        session.setAttribute("username", Username);
        LogInfo = logDAO.checkValidUser(Username, Password);
        return LogInfo;
    }
    
    @RequestMapping("Forgot.do")
    public ModelAndView ForgotPassword() {
        ModelAndView obj = new ModelAndView("ForgotPassword");
        return obj;

    }
   //---------------Function For Redirect Admin Dashboard-----------------
      @RequestMapping("Admin.do")
      public ModelAndView AdminDashboard() {
        ModelAndView adm = new ModelAndView("Admin");
        return adm;

    }
//    =====================Function For User Signup=======================================
     @RequestMapping("signup.do")
      public ModelAndView Signup() {
        ModelAndView obj = new ModelAndView("UserRegistration");
        return obj;

    }
      
//      =======================Check email Already ID exisit==============================
      
    @RequestMapping(value = "checkEmail.do",method = RequestMethod.POST)
    @ResponseBody
    public List checkEmail(@RequestParam(value = "emailId") String Email,HttpSession session) {
        List email;
        email = logDAO.checkEmailIdExist(Email);
        return email;
    }
    
      
      
}
