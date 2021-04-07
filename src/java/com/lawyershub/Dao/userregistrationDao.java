/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Lawyertype;
import com.lawyershub.Model.Userregistration;
import com.lawyershub.common.ImageSerilizer;
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
public class userregistrationDao {
    
    @Autowired
    public SessionFactory sessionFactory;
    
    
        public int save(Userregistration objUser) {
        return (Integer) sessionFactory.getCurrentSession().save(objUser);
    }
          
     
     
      public int update(Userregistration objUser) {
        sessionFactory.getCurrentSession().update(objUser);
        return objUser.getId();
    }
      
      
      public Userregistration getById(int id) {
        return (Userregistration) sessionFactory.getCurrentSession().get(Userregistration.class, id);
    }

      
      
      
      
          public List getUserImage(String email) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				Userregistration.class);
                c.add(Restrictions.eq("email", email));
		c.setProjection(Projections.projectionList()                      
		.add(Projections.property("image"), "Image")

		);

		c.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		ImageSerilizer im = new ImageSerilizer();
		return im.imageSerilizer(c.list(), new String[] { "Image" });

	}
          
          
            //Function Edit
       public List<Object> getEditProfileDetails(String username) {

        Criteria c = sessionFactory.getCurrentSession().createCriteria(Userregistration.class);
        c.createAlias("Dist", "Dist", CriteriaSpecification.INNER_JOIN);
        c.createAlias("Town", "Town", CriteriaSpecification.INNER_JOIN);
        c.add(Restrictions.eq("email", username));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "ID")// 1
                .add(Projections.property("address"), "Address")// 2
                .add(Projections.property("mobile"), "Mobile")// 2
                .add(Projections.property("email"), "Email")// 2
                .add(Projections.property("Dist.district"), "District")// 4
                .add(Projections.property("Town.town"), "Town")
                .add(Projections.property("image"), "Image"));// 20
        c.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        ImageSerilizer im = new ImageSerilizer();
        return im.imageSerilizer(c.list(), new String[]{"Image"});

    }

}
