package org.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Borrower;
import org.model.LibraryCatalogue;
import org.model.LibraryCatalogueBorrower;
import org.model.LibraryCatalogueBorrowerId;
import org.util.HibernateUtil;

import java.time.LocalDate;
import java.time.Year;

public class BorrowerService {
    public static void registerUser(String name, String email) {
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Borrower existingUser = existingUser(session, email);
            if (existingUser != null) {
                System.out.println("User is already registered");
                return;
            }
            tx = session.beginTransaction();
            Borrower newUser = new Borrower();
            newUser.setName(name);
            newUser.setEmail(email);

            int year = Year.now().getValue();
            int randomNum = (int) (Math.random() * 10000);
            newUser.setMembership_id("LIB" + year + "-" + String.format("%04d", randomNum));
            session.persist(newUser);
            tx.commit();
        }
    }

    public static Borrower existingUser(Session session, String email) {
        return session.createQuery("from Borrower b where b.email=:email", Borrower.class)
                .setParameter("email", email).uniqueResult();
    }

    public static void borrowBook(String email, String isbn) {
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Borrower borrower = session.createQuery("from Borrower b where b.email=:email", Borrower.class).setParameter("email", email).getSingleResult();
            LibraryCatalogue libraryCatalogue = session.createQuery("from LibraryCatalogue l where l.isbn=:isbn", LibraryCatalogue.class).setParameter("isbn", isbn).getSingleResult();

            LibraryCatalogueBorrowerId libraryCatalogueBorrowerId = new LibraryCatalogueBorrowerId();
            libraryCatalogueBorrowerId.setBorrowerId(borrower.getId());
            libraryCatalogueBorrowerId.setLibraryCatalogueId(libraryCatalogue.getId());
            libraryCatalogueBorrowerId.setIssueDate(LocalDate.now());

            LibraryCatalogueBorrower libraryCatalogueBorrower = new LibraryCatalogueBorrower();
            libraryCatalogueBorrower.setId(libraryCatalogueBorrowerId);
            libraryCatalogueBorrower.setBorrower(borrower);
            libraryCatalogueBorrower.setLibraryCatalogue(libraryCatalogue);

            session.persist(libraryCatalogueBorrower);
            tx.commit();
        }
    }

    public static void returnBook(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Borrower borrower = session.createQuery("from Borrower b where b.email=:email", Borrower.class).setParameter("email", email).getSingleResult();
            LibraryCatalogueBorrower libraryCatalogueBorrower = session.createQuery("from LibraryCatalogueBorrower l where l.borrower=:borrower", LibraryCatalogueBorrower.class).setParameter("borrower", borrower).getSingleResult();
            if (libraryCatalogueBorrower != null && libraryCatalogueBorrower.getReturnDate() == null) {
                libraryCatalogueBorrower.setReturnDate(LocalDate.now());
            } else {
                System.out.println("Book is already returned by borrower");
            }
            session.persist(libraryCatalogueBorrower);
            tx.commit();
        }
    }
}
