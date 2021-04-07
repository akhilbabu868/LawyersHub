/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.controller;

import com.lawyershub.Dao.showReviewDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
public class showreviewsController {
 
    @Autowired
    showReviewDao ReviewDAO;
    
    
    @RequestMapping("showreviews.do")
    public ModelAndView showReview() {
    ModelAndView obj = new ModelAndView("viewReviews");
    return obj;

    }
    
    
    
    @RequestMapping(value = "getReviewByID.do", method = RequestMethod.POST)
    @ResponseBody
    public List getfullreviewsList(@RequestParam(value="Lawyer")Integer ID) {
        System.out.println("hiiii");
        List fullreviewsList = ReviewDAO.getReviewByID(ID);
        System.out.println("//List" + fullreviewsList);
        return fullreviewsList;
    }
}
