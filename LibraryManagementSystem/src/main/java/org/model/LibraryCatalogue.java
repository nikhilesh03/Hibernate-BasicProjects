package org.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "library_catalogue")
public class LibraryCatalogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String isbn;
    private LocalDate year;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "library_catalogue_author",
            joinColumns = @JoinColumn(name = "library_catalogue_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> author = new HashSet<>();

    @OneToMany(mappedBy = "libraryCatalogue",cascade = CascadeType.ALL)
    private Set<LibraryCatalogueBorrower> libraryCatalogueBorrowers=new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getYear() {
        return year;
    }

    @PrePersist
    protected void createDate(){
        this.year=LocalDate.now();
    }

    public void setYear(Date date) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Set<Author> author) {
        this.author = author;
    }
}
