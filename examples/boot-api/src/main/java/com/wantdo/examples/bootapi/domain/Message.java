package com.wantdo.examples.bootapi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    public Account receiver;

    public String message;

    public Date receiveDate;

    public Message() {
    }

    public Message(Account receiver, String message, Date receiveDate) {
        this.receiver = receiver;
        this.message = message;
        this.receiveDate = receiveDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
