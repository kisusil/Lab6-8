package ru.lab6.server;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.lab6.common.humanbeing.HumanBeing;

import java.util.List;

public class HumanBeingDaoImpl implements HumanBeingDao {
    public void save(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(humanBeing);
        tx1.commit();
        session.close();
    }

    public void update(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(humanBeing);
        tx1.commit();
        session.close();
    }

    public void delete(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(humanBeing);
        tx1.commit();
        session.close();
    }
}
