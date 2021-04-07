/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Qualificationmaster;
import com.lawyershub.Model.Qualificationmaster;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
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
public class qualificationmasterDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void saveQualificationMaster(Qualificationmaster objQualification) {
        sessionFactory.getCurrentSession().save(objQualification);
    }

    public int updateQualification(Qualificationmaster objQualification) {
        sessionFactory.getCurrentSession().update(objQualification);
        return objQualification.getId();
    }

    public Qualificationmaster getById(int id) {
        return (Qualificationmaster) sessionFactory.getCurrentSession().get(Qualificationmaster.class, id);
    }

     
     public int updateStatus(Qualificationmaster objQualification) {
        sessionFactory.getCurrentSession().update(objQualification);
        return objQualification.getId();
    }
    
    
     //Fucntion For Load Data From Datat Table
    public List getSearchqulification(int iDisplayStart, int iDisplayLength, String sSearch) {

        String str = "SELECT id as id,qualification as name, status as Status from qualificationmaster  ";
        if (!sSearch.isEmpty()) {
            str += " where qualification like '%" + sSearch + "%' or (qualification like '%" + sSearch + "%'  ) ";
        }
        String condition = "order by ID desc";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str + condition);
        query.addScalar("id");
        query.addScalar("name");
        query.addScalar("Status");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(iDisplayStart);
        query.setMaxResults(iDisplayLength);
        return query.list();
    }


    
    @SuppressWarnings("unchecked")
    public Map getSearchqulificationCount(String sSearch) {
        Map mapCount = new HashMap();

        String query = "SELECT \n"
                + "    COUNT(ID) count\n"
                + "FROM\n"
                + "   qualificationmaster";
        boolean isAndNeeded = false;
        if (!sSearch.isEmpty()) {
            query += " where qualification like '%" + sSearch + "%' or (qualification like '%" + sSearch + "%'  ) ";
        }
        SQLQuery c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        Map countList = (Map) c.list().get(0);
        mapCount.put("CountAfterFilter", countList.get("count"));

        query = "SELECT \n"
                + "    COUNT(ID) count \n"
                + "FROM\n"
                + "  qualificationmaster";
//        if (!sSearch.isEmpty()) {
//            query += " where LicenseNo like '%" + sSearch + "%' or (facilityName like '%" + sSearch + "%'  ) ";
//        }
        c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        countList = (Map) c.list().get(0);
        mapCount.put("CountBeforeFilter", countList.get("count"));

        return mapCount;

    }
    
     @SuppressWarnings("unchecked")
    public List<String> getQualification() {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Qualificationmaster.class);
        c.add(Restrictions.eq("status", 1));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"))
                .add(Projections.property("qualification")));
        return c.list();
    }
    
}
