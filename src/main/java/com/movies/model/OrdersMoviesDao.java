package com.movies.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class OrdersMoviesDao {
    @Autowired
    SessionFactory sessionFactory;
    
    public void save(OrdersMovies om) {
        Session session = sessionFactory.getCurrentSession();
        session.save(om);        
    }
    
    public List<OrdersMovies> findByOrder(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<OrdersMovies> result = session.getNamedQuery("OrdersMovies.findByOrder").setInteger("id", id).list();
        return result;
    }

}
