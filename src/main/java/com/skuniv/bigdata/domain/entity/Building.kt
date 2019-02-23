package com.skuniv.bigdata.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.Builder
import java.io.Serializable
import javax.persistence.*

@Entity
data class Building(
        @Id
        @GeneratedValue
        var no: Long? = null,
        var city: Int,
        var groop: Int,
        var dong: String,
        var name: String,
        var area: Double,
        var floor: Int,
        var type: Int,
        @Column(name = "building_num")
        var buildingNum: String,
        @Column(name = "construct_year")
        var constructYear: String,
        @OneToMany(mappedBy = "building", fetch = FetchType.EAGER)
        @JsonIgnoreProperties("building")
        val bargainDates: Set<BargainDate>,
        @OneToMany(mappedBy = "building", fetch = FetchType.EAGER)
        @JsonIgnoreProperties("building")
        val charterDates: Set<CharterDate>,
        @OneToMany(mappedBy = "building", fetch = FetchType.EAGER)
        @JsonIgnoreProperties("building")
        val rentDates: Set<RentDate>
) : Serializable