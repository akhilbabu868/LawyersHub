/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Districtmaster;
import com.lawyershub.Model.Townmaster;
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
public class districtmasterDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public void saveDistrictMaster(Districtmaster objDistrict) {
        sessionFactory.getCurrentSession().save(objDistrict);
    }

    public int updateDistrict(Districtmaster objDistrict) {
        sessionFactory.getCurrentSession().update(objDistrict);
        return objDistrict.getId();
    }

    public Districtmaster getById(int id) {
        return (Districtmaster) sessionFactory.getCurrentSession().get(Districtmaster.class, id);
    }

    
    
    //Fucntion For Load Data From Datat Table
    public List getSearchdistrict(int iDisplayStart, int iDisplayLength, String sSearch) {

        String str = "SELECT id as id,district as name, status as Status from districtmaster  ";
        if (!sSearch.isEmpty()) {
            str += " where district like '%" + sSearch + "%' or (district like '%" + sSearch + "%'  ) ";
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
    public Map getSearchDistrictCount(String sSearch) {
        Map mapCount = new HashMap();

        String query = "SELECT \n"
                + "    COUNT(ID) count\n"
                + "FROM\n"
                + "   districtmaster";
        boolean isAndNeeded = false;
        if (!sSearch.isEmpty()) {
            query += " where district like '%" + sSearch + "%' or (district like '%" + sSearch + "%'  ) ";
        }
        SQLQuery c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        Map countList = (Map) c.list().get(0);
        mapCount.put("CountAfterFilter", countList.get("count"));

        query = "SELECT \n"
                + "    COUNT(ID) count \n"
                + "FROM\n"
                + "  districtmaster";
//        if (!sSearch.isEmpty()) {
//            query += " where LicenseNo like '%" + sSearch + "%' or (facilityName like '%" + sSearch + "%'  ) ";
//        }
        c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        countList = (Map) c.list().get(0);
        mapCount.put("CountBeforeFilter", countList.get("count"));

        return mapCount;

    }
    
    
     public int updateStatus(Districtmaster objDist) {
        sessionFactory.getCurrentSession().update(objDist);
        return objDist.getId();
    }
     
     
    @SuppressWarnings("unchecked")
    public List<String> getdistrict() {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Districtmaster.class);
        c.add(Restrictions.eq("status", 1));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"))
                .add(Projections.property("district")));
        return c.list();
    }
    
     @SuppressWarnings("unchecked")
    public List<String> getTown(Integer districtID) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Townmaster.class);
        c.add(Restrictions.eq("status", 1));
        c.add(Restrictions.eq("districtid", districtID));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"))
                .add(Projections.property("town")));
        return c.list();
    }
    
}
