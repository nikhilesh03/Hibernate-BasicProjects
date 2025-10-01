package org.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.model.Blog;
import org.model.Users;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Users.class);
            cfg.addAnnotatedClass(Blog.class);
            cfg.addAnnotatedClass(Comment.class);

            return cfg.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Session creation failed: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
