/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;


import com.lawyershub.Model.Login;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class sendEmailDao {
    
    
     @Autowired
     private SessionFactory sessionFactory;  
     
     
     
    @SuppressWarnings("unchecked")
    public List <String> getPassword(String Email) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Login.class);
        c.add(Restrictions.eq("username", Email));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("password")));
        return c.list();
    }

     
}
