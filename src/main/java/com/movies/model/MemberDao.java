package com.movies.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class MemberDao {
    @Autowired
    SessionFactory sessionFactory;
    
    public void insert(Member1 m) {
        Session session = sessionFactory.getCurrentSession();
        session.save(m);
        
    }
    
    public Member1 findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Member1 m = (Member1) session.getNamedQuery("Member1.findByEmail").setString("email", email).uniqueResult();
        return m;
    }
    
    public Member1 find(String firstName, String lastName, String address, String city, String postalCode, String email) {
        Session session = sessionFactory.getCurrentSession();
        Member1 m = (Member1) session.getNamedQuery("Member1.find").setString("firstName", firstName).setString("lastName", lastName).setString("address", address).setString("city", city).setString("postalCode", postalCode).setString("email", email).uniqueResult();
        return m;
    }
    
    public List<Member1> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Member1> result= session.createCriteria(Member1.class).list();
        return result;
    }
    
    public Member1 findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Member1 m = (Member1)session.getNamedQuery("Member1.findById").setInteger("id", id).uniqueResult();
        return m;
    }
    
    public void update(Member1 m) {
        sessionFactory.getCurrentSession().update(m);
    }
    
    public List<Member1> findByPage(int page) {
        int perpage = 3;
        Session session = sessionFactory.getCurrentSession();
        List<Member1> result = session.createQuery("from Member1").setFirstResult(page * perpage).setMaxResults(perpage).list();
        return result;
    }
    
    public Long pages() {
        Long result = ((Double)Math.ceil((Long)sessionFactory.getCurrentSession().createQuery("select count(id) from Member1").uniqueResult()/3)).longValue();
         return result;
    }

}
