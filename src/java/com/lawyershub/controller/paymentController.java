/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.buypackageDao;
import com.lawyershub.Model.Booking;
import com.lawyershub.Model.Payment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
public class paymentController {
    
    
    @Autowired
    buypackageDao buypkg;
    
    @Autowired
    private JavaMailSender mailSender;
    
   @RequestMapping("paymentview.do")
   public ModelAndView payment(){
   ModelAndView payment =new ModelAndView("Payment");
   return payment;
   }
   
   
   @RequestMapping("transaction.do")
   public ModelAndView transaction(){
   ModelAndView transaction =new ModelAndView("OTP");
   return transaction;
   }
   
   
   //Send OTP 
   
   @RequestMapping("sendOTP.do")
   public Integer OtpSendEmail(@RequestParam("otp") String Otp,
                     @RequestParam("email") String EmailId,
                     HttpServletRequest request) {
		// takes input from e-mail form
               Integer flag =1;
		String recipientAddress = EmailId;
                System.out.println("Otp"+Otp);
		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setText("Your OTP is "+Otp);
		// sends the e-mail
		mailSender.send(email);
		// forwards to the view named "Result"
		return flag;
	}
    
    //Function For Payment
   
    @RequestMapping("paymentconfirm.do")
   public @ResponseBody Map  Paymentprocess(
                     Payment Objpayment,
                     @RequestParam("user")  Integer UserId,
                     @RequestParam("refID") Integer refID,
                     @RequestParam("pkgID") Integer packgID,
                     HttpServletRequest request) {
		    
                     Map objMap = new HashMap();
                     Date date = new Date();
                     SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                     String strDate = formatter.format(date);
                     formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                     strDate = formatter.format(date);
                     System.out.println("Date Format" + strDate);

                    Objpayment.setUserid(UserId);
                    Objpayment.setReferenceid(refID);
                    Objpayment.setPackageid(packgID);
                    Objpayment.setPaymentdate(date);
                    Objpayment.setStatus(1);
                    int sucess = buypkg.save(Objpayment);
                    if(sucess ==1){
                        buypkg.updatePackage(packgID,UserId);
                    }
                    objMap.put("flag", 1);
                    return objMap;
	}
}
