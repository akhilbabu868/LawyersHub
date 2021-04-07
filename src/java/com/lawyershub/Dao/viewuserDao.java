/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Districtmaster;
import com.lawyershub.Model.Login;
import com.lawyershub.Model.Userregistration;
import com.lawyershub.common.ImageSerilizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
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
public class viewuserDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
      public Login getById(int id) {
        return (Login) sessionFactory.getCurrentSession().get(Login.class, id);
    }

    
    public List getSearchuser(int iDisplayStart, int iDisplayLength, String sSearch) {

        String str = "SELECT user.id as id, user.firstname as Fname,\n"
                + "    user.lastname as Lname,\n"
                + "    user.email as EmailId,\n"
                + "    lg.status As loginStatus,lg.id As loginID from userregistration user \n"
                + "    inner join login  lg on user.email = lg.username ";
        if (!sSearch.isEmpty()) {
            str += " where firstname like '%" + sSearch + "%' or (email like '%" + sSearch + "%'  ) ";
        }
        String condition = "order by ID desc";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str + condition);
        query.addScalar("id");
        query.addScalar("Fname");
        query.addScalar("Lname");
        query.addScalar("EmailId");
        query.addScalar("loginStatus");
        query.addScalar("loginID");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(iDisplayStart);
        query.setMaxResults(iDisplayLength);
        return query.list();
    }
    
    
    
    @SuppressWarnings("unchecked")
    public Map getSearchUserCount(String sSearch) {
        Map mapCount = new HashMap();

        String query = "SELECT \n"
                + "    COUNT(user.ID) count\n"
                + "FROM\n"
                + "   userregistration user inner join login  lg on user.email = lg.username";
        boolean isAndNeeded = false;
        if (!sSearch.isEmpty()) {
            query += " where firstname like '%" + sSearch + "%' or (email like '%" + sSearch + "%'  ) ";
        }
        SQLQuery c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        Map countList = (Map) c.list().get(0);
        mapCount.put("CountAfterFilter", countList.get("count"));

        query = "SELECT \n"
                + "    COUNT(user.ID) count \n"
                + "FROM\n"
                + "  userregistration user inner join login  lg on user.email = lg.username";
//        if (!sSearch.isEmpty()) {
//            query += " where LicenseNo like '%" + sSearch + "%' or (facilityName like '%" + sSearch + "%'  ) ";
//        }
        c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        countList = (Map) c.list().get(0);
        mapCount.put("CountBeforeFilter", countList.get("count"));

        return mapCount;

    }
    
//     public List getuserdetails(int ID) {
//
//        String str = "SELECT user.id as id,\n"
//                + "    user.firstname as Fname,\n"
//                + "    user.lastname as Lname,\n"
//                + "    user.dob as Dob,\n"
//                + "    user.gender as Gender,\n"
//                + "    user.address as Address,\n"
//                + "    user.mobile as Mobile,\n"
//                + "    dt.district as District,\n"
//                + "    tn.town as Town\n"
//                + "from\n"
//                + "    userregistration user\n"
//                + "        inner join\n"
//                + "    districtmaster dt ON user.districtid = dt.id\n"
//                + "        inner join\n"
//                + "    townmaster tn ON dt.id = tn.districtid\n"
//                + "where  user.id ='" + ID + "' \n";
//
//        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str);
//        query.addScalar("id");
//        query.addScalar("Fname");
//        query.addScalar("Lname");
//        query.addScalar("Dob");
//        query.addScalar("Gender");
//        query.addScalar("Address");
//        query.addScalar("Mobile");
//        query.addScalar("District");
//        query.addScalar("Town");
//        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//        return query.list();
//    }

    //criteria code
     public List<Object> getUserDetails(int ID) {

        Criteria c = sessionFactory.getCurrentSession().createCriteria(Userregistration.class);
        c.createAlias("Dist", "Dist", CriteriaSpecification.INNER_JOIN);
        c.createAlias("Town", "Town", CriteriaSpecification.INNER_JOIN);
        c.add(Restrictions.eq("id", ID));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"))// 1
                .add(Projections.property("firstname"),"Fname")// 2
                .add(Projections.property("lastname"),"Lname")// 2
                .add(Projections.property("dob"),"Dob")// 2
                .add(Projections.property("gender"),"Gender")// 2
                .add(Projections.property("address"),"Address")// 2
                .add(Projections.property("mobile"),"Mobile")// 2
                .add(Projections.property("Dist.district"),"District")// 4
                .add(Projections.property("Town.town"),"Town")
                .add(Projections.property("image"),"Image"));// 20
        c.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        ImageSerilizer im = new ImageSerilizer();
        return im.imageSerilizer(c.list(), new String[]{"Image"});

    }

    
    
    
    
     public int updateStatus(Login objLogin) {
        sessionFactory.getCurrentSession().update(objLogin);
        return objLogin.getId();
    }
    
    
}
