package com.capillary.crawler.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;
@Entity
@Data
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // unique for each table
    Integer id;

    @Column(unique = true)
    String source;

    @OneToMany(mappedBy = "")
    List<Link> links;

    @OneToMany
    List<Phone> phoneNumbers;

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void setPhoneNumbers(List<Phone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
