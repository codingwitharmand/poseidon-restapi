package com.nnk.springboot.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
@Data
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bidListId;
    private String account;
    private String type;
    private Double bidQuantity;
    private Double askQuantity;
    private Double bid;
    private Double ask;
    private String benchmark;
    private Timestamp bidListDate;
    private String commentary;
    private String security;
    private String status;
    private String trader;
    String book;
    private String creationName;
    private Timestamp creationDate;
    String revisionName;
    private Timestamp revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;
}
