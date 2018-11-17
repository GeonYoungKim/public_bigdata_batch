package com.skuniv.bigdata.domain.entity

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class RentDate(
        @Id
        @ManyToOne
        val building: Building? = null,
        @Id
        val date: Date? = null,
        @Column(name = "guarantee_price")
        val guaranteePrice: String? = null,
        @Column(name = "monthly_price")
        val monthlyPrice: String? = null
) : Serializable