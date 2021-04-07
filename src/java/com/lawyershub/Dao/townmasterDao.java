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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class townmasterDao {
    
    
     @Autowired
    private SessionFactory sessionFactory;

    public void saveTownmasterMaster(Townmaster objTown) {
        sessionFactory.getCurrentSession().save(objTown);
    }

    public int updateTownmaster(Townmaster objTown) {
        sessionFactory.getCurrentSession().update(objTown);
        return objTown.getId();
    }

    public Townmaster getById(int id) {
        return (Townmaster) sessionFactory.getCurrentSession().get(Townmaster.class, id);
    }
    
     public List getSearchTown(int iDisplayStart, int iDisplayLength, String sSearch) {

         String str = "SELECT \n"
                 + "    tn.id as id,\n"
                 + "    tn.town as Town,\n"
                 + "    tn.status as Status,\n"
                 + "    dt.district as District\n"
                 + "from\n"
                 + "    townmaster tn\n"
                 + "        inner join\n"
                 + "    districtmaster dt ON tn.districtid = dt.id ";
         if (!sSearch.isEmpty()) {
            str += " where tn.town like '%" + sSearch + "%' or (dt.district  like '%" + sSearch + "%'  ) ";
        }
        String condition = "order by ID desc";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str + condition);
        query.addScalar("id");
        query.addScalar("Town");
        query.addScalar("District");
        query.addScalar("Status");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(iDisplayStart);
        query.setMaxResults(iDisplayLength);
        return query.list();
    }

    
        @SuppressWarnings("unchecked")
    public Map getSearchTownCount(String sSearch) {
        Map mapCount = new HashMap();

        String query = "SELECT \n"
                + "    COUNT(tn.ID) count\n"
                + "FROM\n"
                + "   townmaster tn inner join  districtmaster dt ON tn.districtid = dt.id ";
        boolean isAndNeeded = false;
        if (!sSearch.isEmpty()) {
            query += " where tn.town like '%" + sSearch + "%' or (dt.district like '%" + sSearch + "%'  ) ";
        }
        SQLQuery c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        Map countList = (Map) c.list().get(0);
        mapCount.put("CountAfterFilter", countList.get("count"));

        query = "SELECT \n"
                + "    COUNT(tn.ID) count \n"
                + "FROM\n"
                + "  townmaster tn inner join  districtmaster dt ON tn.districtid = dt.id";
//        if (!sSearch.isEmpty()) {
//            query += " where LicenseNo like '%" + sSearch + "%' or (facilityName like '%" + sSearch + "%'  ) ";
//        }
        c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        countList = (Map) c.list().get(0);
        mapCount.put("CountBeforeFilter", countList.get("count"));

        return mapCount;

    }
    
    
     public int updateStatus(Townmaster objTown) {
        sessionFactory.getCurrentSession().update(objTown);
        return objTown.getId();
    }
    
}
