/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.bookingDao;
import com.lawyershub.Model.Booking;
import com.lawyershub.Model.Login;
import com.lawyershub.Model.Userregistration;
import java.io.IOException;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
public class bookingController {
    
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    bookingDao BookDAO;
    
     @RequestMapping("booking.do")
     public ModelAndView booking() {
        ModelAndView obj = new ModelAndView("booking");
        return obj;

    }
     
    @RequestMapping(value = "getuser.do",method = RequestMethod.POST)
    @ResponseBody
    public List getUserID(@RequestParam(value = "user", required = true) String email) {
        System.out.println("hello"+email);
        List userID = BookDAO.getUserId(email);
        System.out.println("//List" + userID);
        return userID;
    }
    
    //Function For checkBookingDate
    @RequestMapping(value = "listdates.do",method = RequestMethod.POST)
    @ResponseBody
    public List checkbookingDate(
        @RequestParam(value = "user", required = true) String username,
        @RequestParam(value = "bookingdate", required = true) String bookDate) throws ParseException {
        
        System.out.println("//dates"+bookDate);
        System.out.println("//username"+username);
        
        List userID = null;
        userID = BookDAO.ListDates(username,bookDate);
        System.out.println("//List" + userID);
        return userID;
    }
    
    
    //Function For Booking
    @RequestMapping("saveBooking.do")
    public @ResponseBody
    Map save(Booking Obj,
            @RequestParam(value = "User", required = false) Integer User,
            @RequestParam(value = "Lawyer", required = false) Integer Lawyer,
            @RequestParam(value = "BookDate", required = false) String BookingDate,
            @RequestParam(value = "Title", required = false) String title,
            @RequestParam(value = "Description", required = false) String desc,
            SessionStatus status, HttpSession session) throws ParseException {

           Map objMap = new HashMap();
        System.out.println("//User" +User);
        System.out.println("//Lawyer" +Lawyer);
        System.out.println("//BookingDate" +BookingDate);
        System.out.println("//title" +title);
        System.out.println("//desc" +desc);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = BookingDate;
        Date date = sdf.parse(dateInString);
        System.out.println("Actualdate"+date); //Prints 26/10/2015
      
        int mapCount = BookDAO.getBookingCount(Lawyer,date);
        System.out.println("///mapCount"+mapCount);
        
        if (mapCount > 10) {
            objMap.put("flag", 0);
            System.out.println("//mapCount"+mapCount);
        } else {
            Obj.setLawyerid(Lawyer);
            Obj.setUserid(User);
            Obj.setBookingdate(date);
            Obj.setTitle(title);
            Obj.setDescription(desc);
            Obj.setStatus(1);
            BookDAO.save(Obj);
            objMap.put("flag", 1);
        }
       return objMap;
    }

    
    
    
    //Enable And Disable Booking Status
       @RequestMapping("/setbookingStatus.do")
    public @ResponseBody
    int setMarketingMasterStatus(@RequestParam("id") int id,
            @RequestParam("status") int status,
            HttpSession session) {
        int flag = 0;
        try {
            Booking objBooking = BookDAO.getById(id);
            if (status == 1) {
                objBooking.setId(id);
                objBooking.setStatus(AppConstants.STATUS_INACTIVE);
                int sucess = BookDAO.updateStatus(objBooking);
                if(sucess == 1){
                    
//                String recipientAddress = ;
                String pass="Your Password is Booking Suc";
		SimpleMailMessage email = new SimpleMailMessage();
//		email.setTo(recipientAddress);
//		email.setSubject(subject);
		email.setText(pass);
		// sends the e-mail
		mailSender.send(email);
                    
                }
                
                
                flag = 1;
            } else {
                objBooking.setId(id);
                objBooking.setStatus(AppConstants.STATUS_ACTIVE);
                BookDAO.updateStatus(objBooking);
                flag = 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    
    
    
    
    
    
    
}
