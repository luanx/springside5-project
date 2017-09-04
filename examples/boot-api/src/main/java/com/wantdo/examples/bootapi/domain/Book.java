package com.wantdo.examples.bootapi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String doubanId;

    public String title;

    public String status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    public Account owner;

    public Date onboardDate;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    public Account borrower;

    public Date borrowDate;

    public Book() {
    }

    public Book(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
