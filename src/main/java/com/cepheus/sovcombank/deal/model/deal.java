package com.cepheus.sovcombank.deal.model;

import com.cepheus.sovcombank.user.model.Account;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "deals", schema = "private")
public class deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @Column(name = "summary")
    private Float summary;

    @ManyToOne
    private Account account;
}
