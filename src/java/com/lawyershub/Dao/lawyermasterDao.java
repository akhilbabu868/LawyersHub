/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Lawyertype;
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
public class lawyermasterDao {
    
      @Autowired
    private SessionFactory sessionFactory;

    public void saveLawyertypeMaster(Lawyertype objLawyertype) {
        sessionFactory.getCurrentSession().save(objLawyertype);
    }

    public int updateLawyertype(Lawyertype objLawyertype) {
        sessionFactory.getCurrentSession().update(objLawyertype);
        return objLawyertype.getId();
    }

    public Lawyertype getById(int id) {
        return (Lawyertype) sessionFactory.getCurrentSession().get(Lawyertype.class, id);
    }

    
    
    //Fucntion For Load Data From Datat Table
    public List getSearchlawyertype(int iDisplayStart, int iDisplayLength, String sSearch) {

        String str = "SELECT id as id,lawyertype as name,description as Description, status as Status from lawyertype";
        if (!sSearch.isEmpty()) {
            str += " where lawyertype like '%" + sSearch + "%' or (lawyertype like '%" + sSearch + "%'  ) ";
        }
        String condition = "  order by ID desc";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str + condition);
        query.addScalar("id");
        query.addScalar("name");
        query.addScalar("Description");
        query.addScalar("Status");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(iDisplayStart);
        query.setMaxResults(iDisplayLength);
        return query.list();
    }


    
    @SuppressWarnings("unchecked")
    public Map getSearchLawyertypeCount(String sSearch) {
        Map mapCount = new HashMap();

        String query = "SELECT \n"
                + "    COUNT(ID) count\n"
                + "FROM\n"
                + "   lawyertype";
        boolean isAndNeeded = false;
        if (!sSearch.isEmpty()) {
            query += " where lawyertype like '%" + sSearch + "%' or (lawyertype like '%" + sSearch + "%'  ) ";
        }
        SQLQuery c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        Map countList = (Map) c.list().get(0);
        mapCount.put("CountAfterFilter", countList.get("count"));

        query = "SELECT \n"
                + "    COUNT(ID) count \n"
                + "FROM\n"
                + "  lawyertype";
//        if (!sSearch.isEmpty()) {
//            query += " where LicenseNo like '%" + sSearch + "%' or (facilityName like '%" + sSearch + "%'  ) ";
//        }
        c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        countList = (Map) c.list().get(0);
        mapCount.put("CountBeforeFilter", countList.get("count"));

        return mapCount;

    }
    
    
     public int updateStatus(Lawyertype objDist) {
        sessionFactory.getCurrentSession().update(objDist);
        return objDist.getId();
    }
     
     
    @SuppressWarnings("unchecked")
    public List<String> getlawyertype() {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Lawyertype.class);
        c.add(Restrictions.eq("status", 1));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"))
                .add(Projections.property("lawyertype")));
        return c.list();
    }
    
    
    
    
}
