package com.gabriel.dao;

import com.gabriel.entity.FeMember;
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
public class MemberDao implements DaoService<FeMember> {
    @Override
    public List<FeMember> showData() {
        Session session = HibernateUtil.getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(FeMember.class);
        criteriaQuery.from(FeMember.class);
        List<FeMember> membersList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return FXCollections.observableArrayList(membersList);
    }

    @Override
    public int addData(FeMember data) {
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
    public int updateData(FeMember data) {
        int result = 0;
        Session session = HibernateUtil.getSessionFactory();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(data);
            transaction.commit();
            result = 1;
        }catch (HibernateException ex){
            transaction.rollback();
        }
        session.close();
        return result;
    }
}
