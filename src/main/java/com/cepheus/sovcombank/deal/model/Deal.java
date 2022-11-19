package com.cepheus.sovcombank.deal.model;

import com.cepheus.sovcombank.account.model.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "deals", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @Column(name = "summary")
    private Float summary;

    @ManyToOne
    private Account account;

    private Operation operation;
}
