/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;


import com.lawyershub.Dao.sendEmailDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/sendEmail.do")
public class SendEmailController {
    
    @Autowired
	private JavaMailSender mailSender;
    
    @Autowired
    sendEmailDao emailDao;
	
	@RequestMapping(method = RequestMethod.POST)
	public String doSendEmail(HttpServletRequest request) {
		// takes input from e-mail form
		String recipientAddress = request.getParameter("email");
                List searchResult =emailDao.getPassword(recipientAddress);
                System.out.println("valu"+searchResult.get(0));
		// prints debug info
		String pass="Your Password is '"+searchResult.get(0)+"'";
		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
//		email.setSubject(subject);
		email.setText(pass);
		// sends the e-mail
		mailSender.send(email);
		// forwards to the view named "Result"
		return "Result";
	}
    
    
    
    
    
}
