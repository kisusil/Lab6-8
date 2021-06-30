package ru.lab6.server.database.humanbeings;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.database.HibernateSessionFactoryUtil;

import java.util.List;

public class HumanBeingDaoImpl implements HumanBeingDao {
    public synchronized void save(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(humanBeing);
        transaction.commit();
        session.close();
    }

    public synchronized void update(HumanBeing humanBeing) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(humanBeing);
        transaction.commit();
        session.close();
    }

    public synchronized void saveAll(List<HumanBeing> collection) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(collection);
        transaction.commit();
        session.close();
    }

    @Override
    public synchronized HumanBeing get(String name) {
        return getAll()
                .stream()
                .filter(humanBeing -> humanBeing.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public synchronized List<HumanBeing> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<HumanBeing> result = session.createQuery("from HumanBeing", HumanBeing.class).getResultList();
        session.close();
        return result;
    }
}
