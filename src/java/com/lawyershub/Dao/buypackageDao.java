/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Packagemaster;
import com.lawyershub.Model.Payment;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
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
public class buypackageDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
     public List getFullpkgDetails() {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Packagemaster.class);
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"),"ID")
                .add(Projections.property("packagename"),"Packagename")
                .add(Projections.property("description"),"Description")
                .add(Projections.property("rate"),"Rate")
                .add(Projections.property("status"),"Status")
        );
        c.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
         return c.list();

    }
     
      public int save(Payment objPayment) {
        return (Integer) sessionFactory.getCurrentSession().save(objPayment);
      }
      
      public void updatePackage(int pkgId,int id) {
      SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery("update userregistration user set user.packageid='" + pkgId + "' where user.id='" + id + "'");
      q.executeUpdate();
    }
    
}
