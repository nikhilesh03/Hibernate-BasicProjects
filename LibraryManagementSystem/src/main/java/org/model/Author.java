package org.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "author")
    private Set<LibraryCatalogue> libraryCatalogues=new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LibraryCatalogue> getLibraryCatalogues() {
        return libraryCatalogues;
    }

    public void setLibraryCatalogues(Set<LibraryCatalogue> libraryCatalogues) {
        this.libraryCatalogues = libraryCatalogues;
    }
}
