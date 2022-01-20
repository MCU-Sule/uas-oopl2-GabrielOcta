package com.gabriel.dao;

import com.gabriel.entity.FeTransaction;
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
public class TransactionDao implements DaoService<FeTransaction> {
    @Override
    public List<FeTransaction> showData() {
        Session session = HibernateUtil.getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(FeTransaction.class);
        criteriaQuery.from(FeTransaction.class);
        List<FeTransaction> transactionList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return FXCollections.observableArrayList(transactionList);
    }

    @Override
    public int addData(FeTransaction data) {
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
    public int updateData(FeTransaction data) {
        return 0;
    }
}
