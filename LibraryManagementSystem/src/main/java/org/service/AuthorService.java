package org.service;

import org.hibernate.Session;
import org.model.Author;

public class AuthorService {

    public static Author getOrCreateAuthor(Session session, String name) {
        Author existingAuthor = session.createQuery("from Author a where a.name=:name", Author.class).setParameter("name", name).uniqueResult();
        if (existingAuthor != null) {
            return existingAuthor;
        }
        Author author = new Author();
        author.setName(name);
        return author;
    }
}
