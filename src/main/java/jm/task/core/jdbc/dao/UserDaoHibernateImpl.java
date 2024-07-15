package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            try {
                session.createNativeQuery("""
                        CREATE TABLE IF NOT EXISTS `mydbtest`.`users` (
                          `id` tinyint(100) NOT NULL AUTO_INCREMENT, PRIMARY KEY (`id`),
                          `name` VARCHAR(30) NOT NULL,
                          `lastName` VARCHAR(30) NOT NULL,
                          `age` tinyint(100) NOT NULL);""").executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            try {
                session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            try {
                session.save(new User(name, lastName, age));
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            try {
                session.delete(session.get(User.class, id));
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            try {
                userList = session.createQuery("FROM User").getResultList();
                System.out.println(userList);
                session.getTransaction().commit();

            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            try {
                session.createNativeQuery("DELETE FROM users").executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }
}

