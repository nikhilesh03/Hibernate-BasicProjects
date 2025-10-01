package org.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.model.Users;
import org.util.HibernateUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UserService {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void registerUser(String name, String email) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Users users = new Users();
            users.setName(name);
            users.setEmail(email);
            users.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
            session.persist(users);
            tx.commit();
        }
    }

    public void getUserById(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Users users = session.find(Users.class, userId);
            System.out.println("user details:" + users);
        }
    }

    public void deleteUser(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Users users = session.find(Users.class, userId);
            session.remove(users);
            tx.commit();
        }
    }
}
