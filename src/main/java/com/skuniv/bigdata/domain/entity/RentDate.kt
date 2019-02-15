//package com.skuniv.bigdata.domain.entity
//
//import java.io.Serializable
//import java.util.*
//import javax.persistence.*
//
//@Entity
//data class RentDate(
//        @Id
//        @GeneratedValue(strategy = GenerationType.AUTO)
//        val id: Long? =null,
//
//        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
//        val building: Building? = null,
//
//        @Temporal(TemporalType.DATE)
//        val date: Date? = null,
//        @Column(name = "guarantee_price")
//        val guaranteePrice: String? = null,
//        @Column(name = "monthly_price")
//        val monthlyPrice: String? = null
//) : Serializable