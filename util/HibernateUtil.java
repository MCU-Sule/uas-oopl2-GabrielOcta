package com.gabriel.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public class HibernateUtil {
    public static Session getSessionFactory(){
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session;
        session = sessionFactory.openSession();
        return  session;
    }
}
