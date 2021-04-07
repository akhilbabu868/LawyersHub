/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Lawyerregistration;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class lawyerregistrationDao {
    
    @Autowired
    public SessionFactory sessionFactory;
    
      public int save(Lawyerregistration objLawyer) {
        return (Integer) sessionFactory.getCurrentSession().save(objLawyer);
    }
}
