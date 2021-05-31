package ru.lab6.server.database.humanbeings;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.database.HibernateSessionFactoryUtil;
import ru.lab6.server.database.humanbeings.HumanBeingDao;

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
