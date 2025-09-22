package org.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class LibraryCatalogueBorrowerId {

    private int libraryCatalogueId;
    private int borrowerId;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public int getLibraryCatalogueId() {
        return libraryCatalogueId;
    }

    public void setLibraryCatalogueId(int libraryCatalogueId) {
        this.libraryCatalogueId = libraryCatalogueId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryCatalogueBorrowerId)) return false;
        LibraryCatalogueBorrowerId that = (LibraryCatalogueBorrowerId) o;
        return libraryCatalogueId == that.libraryCatalogueId &&
                borrowerId == that.borrowerId &&
                Objects.equals(issueDate, that.issueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryCatalogueId, borrowerId, issueDate);
    }
}
