package com.movies.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class OrderDao {
    @Autowired
    SessionFactory sessionFactory;
    
    public void save(Order1 o) {
        Session session = sessionFactory.getCurrentSession();
        session.save(o);        
    }
    
    public void deliver(Order1 o) {
        Session session = sessionFactory.getCurrentSession();
        o.setDelivered(1);
        session.update(o);
    }

    public Order1 findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Order1 result = (Order1) session.get(Order1.class, id);
        return result;
    }    
        
    public List<Order1> findByPage(int page) {
        int perpage = 5;
        Session session = sessionFactory.getCurrentSession();
        List<Order1> result = session.getNamedQuery("Order1.findAll").setFirstResult(page*perpage).setMaxResults(perpage).list();
        return result;
    }
    
    public Long pages() {
        Session session = sessionFactory.getCurrentSession();
        Long result= ((Double)Math.ceil((Long)session.createQuery("select count(id) from Order1").uniqueResult()/5)).longValue();
        
        return result;
    }

}
