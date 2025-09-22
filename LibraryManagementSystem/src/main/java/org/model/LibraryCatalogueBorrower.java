package org.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="library_catalogue_borrowers")
public class LibraryCatalogueBorrower {

    @EmbeddedId
    private LibraryCatalogueBorrowerId id;

    @ManyToOne
    @MapsId("libraryCatalogueId")
    @JoinColumn(name="library_catalogue_id")
    private LibraryCatalogue libraryCatalogue;

    @ManyToOne
    @MapsId("borrowerId")
    @JoinColumn(name="borrower_id")
    private Borrower borrower;

    //since this is primary key it should in Embeddable class
//    @Column(name = "issue_date")
//    private LocalDate issueDate;

    @Column(name="return_date")
    private LocalDate returnDate;

    public LibraryCatalogueBorrowerId getId() {
        return id;
    }

    public void setId(LibraryCatalogueBorrowerId id) {
        this.id = id;
    }

    public LibraryCatalogue getLibraryCatalogue() {
        return libraryCatalogue;
    }

    public void setLibraryCatalogue(LibraryCatalogue libraryCatalogue) {
        this.libraryCatalogue = libraryCatalogue;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }


}
