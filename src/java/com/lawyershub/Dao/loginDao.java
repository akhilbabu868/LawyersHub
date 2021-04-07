/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Login;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
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
public class loginDao {
      @Autowired
    private SessionFactory sessionFactory;

 public List<Object> checkValidUser(String Username, String Password) {

        Criteria c = sessionFactory.getCurrentSession().createCriteria(Login.class);
        c.add(Restrictions.eq("username", Username));
        c.add(Restrictions.eq("password", Password));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("roleid"),"Role")// 1
                .add(Projections.property("status"),"Status"));
        c.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return c.list();

    }  
 
 
 //Function For CheckEmail ID Exisit
 
 public List<Object> checkEmailIdExist(String email) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Login.class);
        c.add(Restrictions.eq("username", email));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("username"),"Email"));
        c.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return c.list();
    }  
    
    
    
    
     public void saveLogin(Login objLogin) {
        sessionFactory.getCurrentSession().save(objLogin);
    }
}
