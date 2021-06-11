package ru.lab6.server.database.humanbeings;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.database.HibernateSessionFactoryUtil;

import java.util.List;

public class HumanBeingDaoImpl implements HumanBeingDao {
    public void save(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(humanBeing);
        transaction.commit();
        session.close();
    }

    public void update(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(humanBeing);
        transaction.commit();
        session.close();
    }

    public void saveAll(List<HumanBeing> collection) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(collection);
        transaction.commit();
        session.close();
    }
}
