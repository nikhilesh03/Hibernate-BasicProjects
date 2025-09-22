package org.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.model.Author;
import org.model.Borrower;
import org.model.LibraryCatalogue;
import org.model.LibraryCatalogueBorrower;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(LibraryCatalogue.class);
            configuration.addAnnotatedClass(Author.class);
            configuration.addAnnotatedClass(Borrower.class);
            configuration.addAnnotatedClass(LibraryCatalogueBorrower.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("SessionFactory creation failed: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
