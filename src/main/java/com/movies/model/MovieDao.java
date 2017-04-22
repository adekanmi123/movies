package com.movies.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class MovieDao {

    @Autowired
    SessionFactory sessionFactory;

    public List<Movie> find() {
        Session session = sessionFactory.getCurrentSession();
        List<Movie> result = session.getNamedQuery("Movie.findByOnstock").list();

        return result;
    }

    public List<Movie> findByGenre(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Movie> result = session.getNamedQuery("Movie.findByGenre").setInteger("genre", id).list();
        return result;
    }

    public Movie findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Movie result = (Movie) session.get(Movie.class, id);
        return result;
    }

    public List<Movie> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Movie> result = session.getNamedQuery("Movie.findAll").list();
        return result;
    }

    public List<Movie> findByPage(int page) {
        int perpage = 5;
        Session session = sessionFactory.getCurrentSession();
        List<Movie> result = session.createQuery("from Movie").setFirstResult(page * perpage).setMaxResults(perpage).list();
        return result;
    }

    public void insert(Movie m) {
        sessionFactory.getCurrentSession().save(m);
    }

    public void update(Movie m) {
        sessionFactory.getCurrentSession().update(m);
    }
    
    public Long pages() {
        Long result = ((Double)Math.ceil((Long)sessionFactory.getCurrentSession().createQuery("select count(id) from Movie").uniqueResult()/5)).longValue();
         return result;
    }
}
