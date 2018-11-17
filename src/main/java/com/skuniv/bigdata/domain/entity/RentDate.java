package com.skuniv.bigdata.domain.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class RentDate implements Serializable {

    @Id
    @ManyToOne
    private Building building;

    @Id
    private Date date;

    @Column(name = "guarantee_price")
    private String guaranteePrice;

    @Column(name = "monthly_price")
    private String monthlyPrice;
}
