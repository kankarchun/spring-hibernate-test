/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkc.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import kkc.test.model.TestDB;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 1
 */
public class TestDAOImpl implements TestDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(TestDB t) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(t);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TestDB> list() {
        Session session = this.sessionFactory.openSession();
        String hql = "select * FROM test";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(TestDB.class);

        query.setCacheable(true);
        query.setCacheRegion("testDB");
//        query.setFirstResult(1);
//        query.setMaxResults(10);
        List<TestDB> results = query.list();
        session.close();
        return results;
    }

    @Override
    public List<TestDB> select(int id) {
        Session session = this.sessionFactory.openSession();
        String hql = "select * FROM test T WHERE T.id = :id";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(TestDB.class);
//        query.setFirstResult(1);
//        query.setMaxResults(10);
        List<TestDB> results = query.list();
        session.close();
        return results;
    }

    @Override
    public int insert(String name, int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer result = null;
        try {
            tx = session.beginTransaction();
            /*
             String hql = "INSERT INTO test(id, name) values"
             + "(:id,:name)";
             SQLQuery query = session.createSQLQuery(hql);
             query.addEntity(TestDB.class);
             query.setParameter("name", name);
             query.setParameter("id", id);
             result = query.executeUpdate();
             System.out.println("Rows affected: " + result);
             */

//            for (int i = 0; i < 10; i++) { //Batch processing
//                TestDB testDB = new TestDB();
//                testDB.setId(id);
//                testDB.setName(name);
//                session.save(testDB);
//                if (i % 5 == 0) {
//                    session.flush();
//                    session.clear();
//                }
//            }
            TestDB testDB = new TestDB();
            testDB.setId(id);
            testDB.setName(name);
            result = (Integer) session.save(tx);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void update(String name, int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            /*
             String hql = "UPDATE test set name = :name "
             + "WHERE id = :id";
             SQLQuery query = session.createSQLQuery(hql);
             query.addEntity(TestDB.class);
             query.setParameter("name", name);
             query.setParameter("id", id);
             int result = query.executeUpdate();
             System.out.println("Rows affected: " + result);
             */
//            for (int i = 0; i < 10; i++) { //Batch processing
//                TestDB testDB = new TestDB();
//                testDB.setId(id);
//                testDB.setName(name);
//                session.update(testDB);
//                if (i % 5 == 0) {
//                    session.flush();
//                    session.clear();
//                }
//            }

            TestDB testDB = (TestDB) session.get(TestDB.class, id);
            testDB.setName(name);
            session.update(tx);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            /*
             String hql = "DELETE FROM test "
             + "WHERE id = :id";
             SQLQuery query = session.createSQLQuery(hql);
             query.addEntity(TestDB.class);
             query.setParameter("id", id);
             int result = query.executeUpdate();
             System.out.println("Rows affected: " + result);
             */
            TestDB testDB = (TestDB) session.get(TestDB.class, id);
            session.delete(testDB);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<TestDB> criteria() {
        Session session = this.sessionFactory.openSession();

        Criteria cr = session.createCriteria(TestDB.class);
        Criterion salary = Restrictions.gt("id", 1);
        Criterion name = Restrictions.ilike("name", "%test%");

        LogicalExpression orExp = Restrictions.or(salary, name);
        cr.add(orExp);

        LogicalExpression andExp = Restrictions.and(salary, name);
        cr.add(andExp);

        // To sort records in descening order
        cr.addOrder(Order.desc("id"));

        // To sort records in ascending order
        cr.addOrder(Order.asc("id"));

        // To get sum of a property.
        cr.setProjection(Projections.sum("salary"));

//        cr.setFirstResult(1);
//        cr.setMaxResults(10);
        List<TestDB> results = cr.list();
        session.close();
        return results;
    }

}
