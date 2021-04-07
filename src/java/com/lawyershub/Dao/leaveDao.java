/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Leaves;
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
@Transactional
@Repository
public class leaveDao {
 
    @Autowired
    private SessionFactory sessionFactory;

    public void saveLeave(Leaves objLeave) {
        sessionFactory.getCurrentSession().save(objLeave);
    }
    public int updateLeave(Leaves objLeave) {
        sessionFactory.getCurrentSession().update(objLeave);
        return objLeave.getId();
    }
    public void delete(int id) {
        Leaves leave = getById(id);
        sessionFactory.getCurrentSession().delete(leave);
    }

    
    
     public Leaves getById(int id) {
        return (Leaves) sessionFactory.getCurrentSession().get(Leaves.class, id);
    }
    
    public List getLeaves(int iDisplayStart, int iDisplayLength, String username) {

        String str = "SELECT id as id,fromdate as FromDate,todate as ToDate from leaves where lawyerusername like '%" + username + "%'";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str);
        query.addScalar("id");
        query.addScalar("FromDate");
        query.addScalar("ToDate");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(iDisplayStart);
        query.setMaxResults(iDisplayLength);
        return query.list();
    }
    
     @SuppressWarnings("unchecked")
     public Map getLeavesCount(String username) {
        Map mapCount = new HashMap();

        String query = "SELECT \n"
                + "    COUNT(ID) count\n"
                + "FROM\n"
                + "   leaves where lawyerusername like '%" + username + "%'";
        SQLQuery c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        Map countList = (Map) c.list().get(0);
        mapCount.put("CountAfterFilter", countList.get("count"));

        query = "SELECT \n"
                + "    COUNT(ID) count \n"
                + "FROM\n"
                + "   leaves where lawyerusername like '%" + username + "%'";
        c = sessionFactory.getCurrentSession().createSQLQuery(query);
        c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        countList = (Map) c.list().get(0);
        mapCount.put("CountBeforeFilter", countList.get("count"));

        return mapCount;

    }
    
    
    
}
