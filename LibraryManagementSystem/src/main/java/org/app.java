package org;

import org.model.Borrower;
import org.service.AuthorService;
import org.service.BorrowerService;
import org.service.LibraryCatalogueService;
import org.util.HibernateUtil;

import java.util.Scanner;

public class app {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1. Add book to catalogue\n2. See Books in library catalogue" +
                "\n3. Register User\n4. Borrow a book" +
                "\n5.Return a book");
        int ch = in.nextInt();
        in.nextLine();
        switch (ch) {
            case 1:
                addBookToCatalogue();
                break;
            case 2:
                listBooksFromCatalogue();
                break;
            case 3:
                registerUser();
                break;
            case 4:
                borrowBook();
                break;
            case 5:
                returnBook();
                break;
        }
        in.close();
    }

    public static void addBookToCatalogue() {
        System.out.println("enter isbn:");
        String isbn = in.nextLine();
        System.out.println("enter author name:");
        String name = in.nextLine();
        LibraryCatalogueService.addBookToCatalogue(isbn, name);
    }

    public static void listBooksFromCatalogue() {
        LibraryCatalogueService.listBooksFromCatalogue();
    }

    public static void registerUser() {
        System.out.println("enter name:");
        String name = in.nextLine();
        System.out.println("enter email");
        String email = in.nextLine();
        BorrowerService.registerUser(name, email);
    }

    public static void borrowBook() {
        System.out.println("enter user email to check if user is present or not");
        String email = in.nextLine();
        Borrower existingUser = BorrowerService.existingUser(HibernateUtil.getSessionFactory().openSession(), email);
        if(existingUser!=null){
            listBooksFromCatalogue();
            System.out.println("select book from list");
            String isbn=in.nextLine();
            BorrowerService.borrowBook(email,isbn);
        }else {
            System.out.println("user is not found please register first");
        }
    }

    public static void returnBook(){
        System.out.println("enter user email to check if user borrowed book or not");
        String email=in.nextLine();
        BorrowerService.returnBook(email);
    }
}
