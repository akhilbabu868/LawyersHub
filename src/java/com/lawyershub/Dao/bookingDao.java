/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.Dao;

import com.lawyershub.Model.Booking;
import com.lawyershub.Model.Userregistration;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class bookingDao {
    
    @Autowired
    public SessionFactory sessionFactory;
    
    
    public List getUserId(String email) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Userregistration.class);
        c.add(Restrictions.eq("email", email));
        c.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "ID"));
        return c.list();
        }
    
        public int save(Booking obj) {
        return (Integer) sessionFactory.getCurrentSession().save(obj);
      }
        
        
        //List Dates
        public List ListDates(String uname,String bookdate) throws ParseException {
        
        System.out.println("//name"+uname);
        System.out.println("//Frmdate"+bookdate);

        String str = "SELECT * FROM lawyershub.leaves where lawyerusername like '%" + uname + "%'   and fromdate <= '"+bookdate+"'  and todate >= '"+bookdate+"'";
        String condition = "order by ID desc";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(str + condition);
        query.addScalar("id");
        query.addScalar("fromdate");
        query.addScalar("todate");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }
        
        
       public Booking getById(int id) {
        return (Booking) sessionFactory.getCurrentSession().get(Booking.class, id);
    }
   
       
        public int updateStatus(Booking objBooking) {
        sessionFactory.getCurrentSession().update(objBooking);
        return objBooking.getId();
    }
        
        
 
        @SuppressWarnings("unchecked")
        public int getBookingCount(int LawyerID,Date Bookdate) {
        int count = 0;
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(id)\n"
                    + "FROM booking\n"
                    + "WHERE lawyerid = '" + LawyerID + "' and bookingdate = '"+format1.format(Bookdate)+"'");
            List<BigInteger> lst = query.list();

            if (!lst.isEmpty()) {
                count = lst.get(0).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("query" + count);
        return count;
    }
}
