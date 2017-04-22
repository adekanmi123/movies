package com.movies.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class AdminDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public Admin find(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        Admin result = (Admin) session.getNamedQuery("Admin.find").setString("username", username).setString("password", password).uniqueResult(); 
        return result;
    }

}
