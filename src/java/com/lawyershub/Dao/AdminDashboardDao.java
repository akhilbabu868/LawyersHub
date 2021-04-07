/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
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
public class AdminDashboardDao {
   
    @Autowired
    public SessionFactory sessionFactory;
    
    
    
    
       public List getAllUserRegistrationReport() throws ParseException {

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        SQLQuery qry = sessionFactory
                .getCurrentSession()
                .createSQLQuery("SELECT createddate, COUNT(*) as usercount FROM userregistration GROUP BY  CAST(createddate AS DATE )  ORDER BY 2 ")
                .addScalar("createddate", Hibernate.STRING)
                .addScalar("usercount", Hibernate.STRING);
                 qry.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return qry.list();
    }
       
       
       
       public List getAllLawyerRegistrationReport() throws ParseException {

        SQLQuery qry = sessionFactory
                .getCurrentSession()
                .createSQLQuery("SELECT createddate, COUNT(*) as lawyercount FROM lawyerregistration GROUP BY createddate ORDER BY 2 ")
                .addScalar("createddate", Hibernate.STRING)
                .addScalar("lawyercount", Hibernate.STRING);
                 qry.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return qry.list();
    }
       
}
