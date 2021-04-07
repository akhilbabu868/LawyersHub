/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Lawyerregistration;
import com.lawyershub.Model.Userregistration;
import com.lawyershub.common.ImageSerilizer;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */

@Repository
@Transactional
public class lawyerdashboardDao {
    
    
       @Autowired
       private SessionFactory sessionFactory;
    
    
    
    @SuppressWarnings("unchecked")
    public List<String> getLawyerprofiledetails(String username) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Lawyerregistration.class);
        c.add(Restrictions.eq("email", username));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"))
                .add(Projections.property("firstname"))
                .add(Projections.property("lastname"))
                .add(Projections.property("dob"))
                .add(Projections.property("address"))
                .add(Projections.property("email"))
                .add(Projections.property("mobile"))
        
        );
        return c.list();
    }
    //Function For Edit
     public List<Object> getLawyerEditProfileDetails(String username) {

        Criteria c = sessionFactory.getCurrentSession().createCriteria(Lawyerregistration.class);
        c.createAlias("Dist", "Dist", CriteriaSpecification.INNER_JOIN);
        c.add(Restrictions.eq("email", username));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "ID")// 1
                .add(Projections.property("address"), "Address")// 2
                .add(Projections.property("mobile"), "Mobile")// 2
                .add(Projections.property("email"), "Email")// 2
                .add(Projections.property("Dist.district"), "District")// 4
                .add(Projections.property("image"), "Image"));// 20
        c.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        ImageSerilizer im = new ImageSerilizer();
        return im.imageSerilizer(c.list(), new String[]{"Image"});

    }

    
     public List getLawyerImage(String email) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(
                Lawyerregistration.class);
        c.add(Restrictions.eq("email", email));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("image"), "Image")
        );

        c.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        ImageSerilizer im = new ImageSerilizer();
        return im.imageSerilizer(c.list(), new String[]{"Image"});

    }
     
     public Lawyerregistration getById(int id) {
        return (Lawyerregistration) sessionFactory.getCurrentSession().get(Lawyerregistration.class, id);
    }
     
        public int update(Lawyerregistration obj) {
        sessionFactory.getCurrentSession().update(obj);
        return obj.getId();
    }
       //Function FOR Setting Globally LawyerID
        public Integer getRoleIDByUserID(String userName) {
        return (Integer) sessionFactory.getCurrentSession()
                .createCriteria(Lawyerregistration.class)
                .add(Restrictions.eq("email", userName))
                .setProjection(Projections.property("id")).uniqueResult();
    }

          
        
        //Function For Getting LawyerUsername
        public List getLawyerId(Integer lawyerID) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Lawyerregistration.class);
        c.add(Restrictions.eq("id", lawyerID));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("email"), "Username"));
        return c.list();
        }

        

       //Function For List Booking
        public List getBookingDetails(Integer lawyerID) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Lawyerregistration.class);
        c.add(Restrictions.eq("id", lawyerID));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("email"), "Username"));
        return c.list();
        }


    
     
     
     
     
   
}
