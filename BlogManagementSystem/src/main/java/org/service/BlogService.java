package org.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Blog;
import org.model.Users;
import org.util.HibernateUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BlogService {

    public void createBlog(int userId, String title, String content) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Users user = session.find(Users.class, userId);
        if (user == null) {
            System.out.println("cant find the user");
        } else {
            Blog blog1 = new Blog();
            blog1.setContent(content);
            blog1.setTitle(title);
            blog1.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
            blog1.setUser(user);
            session.persist(blog1);
            tx.commit();
        }
    }
}
