package ru.lab6.server.database.humanbeings;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.database.HibernateSessionFactoryUtil;

import java.util.List;

public class HumanBeingDaoImpl implements HumanBeingDao {
    private Session session;
    private Transaction transaction;


    HumanBeingDaoImpl(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    }

    public synchronized void save(HumanBeing humanBeing) {
        session.save(humanBeing);
        transaction.commit();
    }

    public synchronized void update(HumanBeing humanBeing) {
        session.update(humanBeing);
        transaction.commit();
    }

    public synchronized void saveAll(List<HumanBeing> collection) {
        session.saveOrUpdate(collection);
        transaction.commit();
    }

    @Override
    public synchronized HumanBeing get(String name) {

        return null;
    }

    public synchronized List<HumanBeing> getAll () {
        List<HumanBeing> result = session.createQuery("from HumanBeing").getResultList();
        transaction.commit();
        return result;
    }
}
