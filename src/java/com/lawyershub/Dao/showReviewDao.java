/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Rateing;
import com.lawyershub.Model.Userregistration;
import com.lawyershub.common.ImageSerilizer;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class showReviewDao {
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
    
    
    public List getReviewByID(int Id) {
        String str = "SELECT \n"
                + "    rate.id as Id, rate.comment as Review, user.email as Email, user.image as Image\n"
                + "from\n"
                + "    rateing rate\n"
                + "        inner join\n"
                + "    userregistration user ON rate.userid = user.id\n"
                + "where\n"
                + "    rate.lawyerid ='"+Id+"'";
        String condition = "order by ID desc";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str + condition);
        query.addScalar("Id",Hibernate.INTEGER);
        query.addScalar("Review",Hibernate.STRING);
        query.addScalar("Email",Hibernate.STRING);
        query.addScalar("Image",Hibernate.BLOB);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        ImageSerilizer im = new ImageSerilizer();
        return im.imageSerilizer(query.list(), new String[] { "Image" });

        
        
        }
    
}
