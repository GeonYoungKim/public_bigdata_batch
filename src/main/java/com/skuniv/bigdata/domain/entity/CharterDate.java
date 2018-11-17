package com.skuniv.bigdata.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class CharterDate implements Serializable {

    @Id
    @ManyToOne
    private Building building;
    @Id
    private Date date;

    private String price;
}
