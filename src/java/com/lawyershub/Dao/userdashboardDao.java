/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Districtmaster;
import com.lawyershub.Model.Lawyerregistration;
import com.lawyershub.Model.Packagemaster;
import com.lawyershub.Model.Rateing;
import com.lawyershub.Model.Userregistration;
import com.lawyershub.common.ImageSerilizer;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class userdashboardDao {
  
    @Autowired
    private SessionFactory sessionFactory;
    
    
    @SuppressWarnings("unchecked")
    public List<String> getUserprofiledetails(String username) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Userregistration.class);
        c.createAlias("Pkg", "Pkg", CriteriaSpecification.INNER_JOIN);
        c.add(Restrictions.eq("email", username));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"))
                .add(Projections.property("firstname"))
                .add(Projections.property("lastname"))
                .add(Projections.property("dob"))
                .add(Projections.property("address"))
                .add(Projections.property("email"))
                .add(Projections.property("mobile"))
                .add(Projections.property("Pkg.packagename"),"packagename")// 
                .add(Projections.property("Pkg.description"),"description")// 
        
        );
        return c.list();
    }
      @SuppressWarnings("unchecked")
    public List<String> getUserprofiledetailsEdit(Integer userId) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Userregistration.class);
        c.add(Restrictions.eq("id", userId));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"),"ID")
                .add(Projections.property("firstname"),"Fname")
                .add(Projections.property("lastname"),"Lname")
                .add(Projections.property("dob"),"Dob")
                .add(Projections.property("address"),"Address")
                .add(Projections.property("email"),"Email")
                .add(Projections.property("mobile"),"Mobile")
                .add(Projections.property("image"),"Image")
        
        );
        c.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        ImageSerilizer im = new ImageSerilizer();
        return im.imageSerilizer(c.list(), new String[]{"Image"});
    }
       
      public int updateImg(Userregistration uploadFile) {
        sessionFactory.getCurrentSession().update(uploadFile);
        return uploadFile.getId();
    }
      
      
       public List showallLawyers() {

        SQLQuery qury = sessionFactory.getCurrentSession().createSQLQuery("SELECT \n"
                + "    type.lawyertype as typeoflawyer,\n"
                + "    lawyer.id as id,\n"
                + "    lawyer.firstname as Fname,\n"
                + "    lawyer.lastname as Lname,\n"
                + "    lawyer.address as Address,\n"
                + "    lawyer.email as EmailId,\n"
                + "    lawyer.mobile as Mobile,\n"
                + "    lawyer.image as Image,\n"
                + "    qm.qualification as qualification\n"
                + "from\n"
                + "    lawyertype type\n"
                + "        inner join\n"
                + "    lawyerregistration lawyer ON type.id = lawyer.lawyertypeid\n"
                + "        inner join\n"
                + "    qualificationmaster qm ON lawyer.qualificationid = qm.id  order by ID desc");
        qury.addScalar("id", new IntegerType());
        qury.addScalar("Fname", new StringType());
        qury.addScalar("Lname", new StringType());
        qury.addScalar("Address", new StringType());
        qury.addScalar("EmailId", new StringType());
        qury.addScalar("Mobile", new StringType());
        qury.addScalar("qualification", new StringType());
        qury.addScalar("typeoflawyer", new StringType());
        qury.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        ImageSerilizer im = new ImageSerilizer();
        return im.imageSerilizer( qury.list(), new String[]{"Image"});

    }   
      
    public List getFullLawyerImage() {

        SQLQuery qury = sessionFactory.getCurrentSession().createSQLQuery("SELECT \n"
                + "    lawyer.id as id,\n"
                + "    lawyer.firstname as Fname,\n"
                + "    lawyer.lastname as Lname,\n"
                + "    lawyer.address as Address,\n"
                + "    lawyer.email as Email,\n"
                + "    lawyer.mobile as Mobile,\n"
                + "    lawyer.image as Image,\n"
                + "    AVG(rate.rateing) as Rateing\n"
                + "from\n"
                + "    lawyerregistration lawyer\n"
                + "        inner join\n"
                + "    rateing rate ON lawyer.id = rate.lawyerid");
        
        qury.addScalar("id", new IntegerType());
        qury.addScalar("Fname", new StringType());
        qury.addScalar("Lname", new StringType());
        qury.addScalar("Address", new StringType());
        qury.addScalar("Email", new StringType());
        qury.addScalar("Mobile", new StringType());
        qury.addScalar("Image", new StringType());
        qury.addScalar("Rateing", new IntegerType());
        qury.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        ImageSerilizer im = new ImageSerilizer();
        return im.imageSerilizer(qury.list(), new String[]{"Image"});

    }

      public List getFullLawyerImageById(Integer Id) {

        SQLQuery qury = sessionFactory.getCurrentSession().createSQLQuery("SELECT \n"
                + "    lawyer.id as ID,\n"
                + "    lawyer.firstname as Fname,\n"
                + "    lawyer.lastname as Lname,\n"
                + "    lawyer.address as Address,\n"
                + "    lawyer.email as Email,\n"
                + "    lawyer.mobile as Mobile,\n"
                + "    lawyer.image as Image,\n"
                + "    AVG(rate.rateing) as Rateing\n"
                + "from\n"
                + "    lawyerregistration lawyer\n"
                + "        left join\n"
                + "    rateing rate ON lawyer.id = rate.lawyerid where lawyer.lawyertypeid ='"+Id+"'");
        qury.addScalar("ID", Hibernate.INTEGER);
        qury.addScalar("Fname",Hibernate.STRING);
        qury.addScalar("Lname",Hibernate.STRING);
        qury.addScalar("Address",Hibernate.STRING);
        qury.addScalar("Email",Hibernate.STRING);
        qury.addScalar("Mobile",Hibernate.STRING);
        qury.addScalar("Image",Hibernate.BLOB);
        qury.addScalar("Rateing", Hibernate.INTEGER);
        qury.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        ImageSerilizer im = new ImageSerilizer();
        return im.imageSerilizer(qury.list(), new String[]{"Image"});

    }  
       
     //-------------------------------Function For Only Get User ID---------------------------------
        
        public List getUserId(String email) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Userregistration.class);
        c.add(Restrictions.eq("email", email));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "ID"));
        return c.list();
        }

        
//---------------Function For Save Rateing------------------------------------------------------------  
      public void saveRateing(Rateing objRate) {
        sessionFactory.getCurrentSession().save(objRate);
    }    
}
