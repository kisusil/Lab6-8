package ru.lab6.server.database.users;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.lab6.common.user.User;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.database.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDaoImpl implements UserDao{
    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }



    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.clear();
        tx1.commit();
        session.close();
    }

    public void removeById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(session.get("HumanBeing", id));
        tx1.commit();
        session.close();
    }


    @Override
    public User get(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = session.createNamedQuery("Get_by_login", User.class)
                .setParameter("login", login)
                .getSingleResult();
        tx1.commit();
        session.close();
        return user;
    }
}
