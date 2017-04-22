package com.movies.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class GenreDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public List<Genre> find() {
        Session session = sessionFactory.getCurrentSession();
        List<Genre> result = session.createCriteria(Genre.class).list();
        return result;
    }
    
    public Genre findById (int id) {
        Session session = sessionFactory.getCurrentSession();
        Genre result = (Genre) session.get(Genre.class, id);
        return result;
    }
    
    public void update(Genre g) {
        sessionFactory.getCurrentSession().update(g);
    }
    
    public void insert(Genre g) {
        sessionFactory.getCurrentSession().save(g);
    }
}
