/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
public class viewBookingDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
    
    
    public List getSearchBooking(int iDisplayStart, int iDisplayLength, String sSearch,Integer lawyerID) {

        String str = "SELECT \n"
                + "    book.id as id,\n"
                + "    book.bookingdate as Date,\n"
                + "    book.title as Title,\n"
                + "    book.description as des,\n"
                + "    book.status as Status,\n"
                + "    book.userid  as UserId from booking book\n"
                + " where book.lawyerid = '"+lawyerID+"'";
        if (!sSearch.isEmpty()) {
            str += " and  book.bookingdate like '%" + sSearch + "%' or ( book.bookingdate like '%" + sSearch + "%'  ) ";
        }
        String condition = "order by ID desc";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str + condition);
        query.addScalar("id",Hibernate.INTEGER);
        query.addScalar("Date",Hibernate.DATE);
        query.addScalar("Title",Hibernate.STRING);
        query.addScalar("des",Hibernate.STRING);
        query.addScalar("Status",Hibernate.INTEGER);
        query.addScalar("UserId",Hibernate.INTEGER);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(iDisplayStart);
        query.setMaxResults(iDisplayLength);
        return query.list();
    }
}
