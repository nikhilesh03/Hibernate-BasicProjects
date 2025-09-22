package org.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Author;
import org.model.LibraryCatalogue;
import org.util.HibernateUtil;

import java.util.List;

public class LibraryCatalogueService {

    public static void addBookToCatalogue(String isbn, String name) {
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            LibraryCatalogue book = session.find(LibraryCatalogue.class, isbn);
            if (book == null) {
                book = new LibraryCatalogue();
                book.setIsbn(isbn);
            }
            Author author = AuthorService.getOrCreateAuthor(session, name);
            book.getAuthor().add(author);
            author.getLibraryCatalogues().add(book);

            session.persist(book);
            tx.commit();
        }
    }

    public static void listBooksFromCatalogue() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<LibraryCatalogue> books = session.createQuery
                    ("select distinct b from LibraryCatalogue b " +
                            "left join fetch b.author", LibraryCatalogue.class).list();
            System.out.printf("%-10s | %-20s%n", "ISBN", "Author");
            System.out.println("------------+----------------------");

            for (LibraryCatalogue book : books) {
                String authors = book.getAuthor().stream()
                        .map(Author::getName)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("No Author");
                System.out.printf("%-10s | %-20s%n",
                        book.getIsbn(), authors);
            }
        }
    }
}
