package com.skuniv.bigdata.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Building implements Serializable {
    @Id
    @GeneratedValue
    private Long no;

    private int city;
    private int groop;
    private String dong;
    private String name;
    private Double area;
    private int floor;
    private int type;

    @Column(name = "building_num")
    private int buildingNum;

    @Column(name = "construct_year")
    private String constructYear;

    @OneToMany(mappedBy = "building", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("building")
    private Set<BargainDate> bargainDates;

    @OneToMany(mappedBy = "building", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("building")
    private Set<CharterDate> charterDates;

    @OneToMany(mappedBy = "building", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("building")
    private Set<RentDate> rentDates;
}
