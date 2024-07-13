package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class Util {
    private static SessionFactory sessionFactory;

    private Util() {
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = new Configuration()
                .configure(new File("hibernate.cfg.xml"))
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        User user = new User("name", "lastname", (byte) 18);
        session.beginTransaction();
        session.getTransaction().commit();
        return sessionFactory;
    }
}
