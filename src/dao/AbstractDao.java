/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import config.NewHibernateUtil;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Khalid
 */
public class AbstractDao<T> {

    Class entityClass;

    public AbstractDao(Class entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T t) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
    }

    public void update(T t) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
    }

    public void delete(T t) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
    }

    public void find(Object id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.get(entityClass.getClass(), (Serializable) id);
    }

    public List<T> findAll() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        return session.createCriteria("from " + entityClass.getSimpleName()).list();
    }

    public List<T> load(String hql) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        return session.createQuery(hql).list();
    }
}
