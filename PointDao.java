package com.gabriel.dao;

import com.gabriel.entity.FePoint;
import com.gabriel.util.DaoService;
import com.gabriel.util.HibernateUtil;
import javafx.collections.FXCollections;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public class PointDao implements DaoService<FePoint> {
    @Override
    public List<FePoint> showData() {
        Session session = HibernateUtil.getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(FePoint.class);
        criteriaQuery.from(FePoint.class);
        List<FePoint> pointList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return FXCollections.observableArrayList(pointList);
    }

    @Override
    public int addData(FePoint data) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(data);
            transaction.commit();
            result = 1;
        }catch (HibernateException ex){
            transaction.rollback();
        }
        session.close();
        return result;
    }


    @Override
    public int updateData(FePoint data) {
        return 0;
    }
}
