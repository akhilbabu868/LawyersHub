/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Districtmaster;
import com.lawyershub.Model.Packagemaster;
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
public class packagemasterDao {
    
   @Autowired
    private SessionFactory sessionFactory;

    public void savePackageMaster(Packagemaster objDistrict) {
        sessionFactory.getCurrentSession().save(objDistrict);
    }

    public int updatePackage(Packagemaster objDistrict) {
        sessionFactory.getCurrentSession().update(objDistrict);
        return objDistrict.getId();
    }

    public Packagemaster getById(int id) {
        return (Packagemaster) sessionFactory.getCurrentSession().get(Packagemaster.class, id);
    }  
    
    
    
    
    
    //Fucntion For Load Data From Datat Table
    public List getSearchpackage(int iDisplayStart, int iDisplayLength, String sSearch) {

        String str = "SELECT id as id,packagename as name,description as Description,rate as Rate, status as Status from packagemaster  ";
        if (!sSearch.isEmpty()) {
            str += " where packagename like '%" + sSearch + "%' or (packagename like '%" + sSearch + "%'  ) ";
        }
        String condition = "order by ID desc";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str + condition);
        query.addScalar("id");
        query.addScalar("name");
        query.addScalar("Description");
        query.addScalar("Rate");
        query.addScalar("Status");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(iDisplayStart);
        query.setMaxResults(iDisplayLength);
        return query.list();
    }

    
    
      @SuppressWarnings("unchecked")
    public Map getSearchpackageCount(String sSearch) {
        Map mapCount = new HashMap();

        String query = "SELECT \n"
                + "    COUNT(ID) count\n"
                + "FROM\n"
                + "   packagemaster";
        boolean isAndNeeded = false;
        if (!sSearch.isEmpty()) {
            query += " where packagename like '%" + sSearch + "%' or (packagename like '%" + sSearch + "%'  ) ";
        }
        SQLQuery c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        Map countList = (Map) c.list().get(0);
        mapCount.put("CountAfterFilter", countList.get("count"));

        query = "SELECT \n"
                + "    COUNT(ID) count \n"
                + "FROM\n"
                + "  packagemaster";
//        if (!sSearch.isEmpty()) {
//            query += " where LicenseNo like '%" + sSearch + "%' or (facilityName like '%" + sSearch + "%'  ) ";
//        }
        c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        countList = (Map) c.list().get(0);
        mapCount.put("CountBeforeFilter", countList.get("count"));

        return mapCount;

    }
    
    public int updateStatus(Packagemaster objPkg) {
        sessionFactory.getCurrentSession().update(objPkg);
        return objPkg.getId();
    }
    
    
     @SuppressWarnings("unchecked")
    public List<String> getpackage() {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Packagemaster.class);
        c.add(Restrictions.eq("status", 1));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"))
                .add(Projections.property("packagename")));
        return c.list();
    }
    
}
