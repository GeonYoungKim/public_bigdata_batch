//package com.skuniv.bigdata.domain.entity
//
//import java.io.Serializable
//import java.util.*
//import javax.persistence.*
//import javax.persistence.CascadeType.*
//
//@Entity
//data class BargainDate(
//        @Id
//        @GeneratedValue(strategy = GenerationType.AUTO)
//        val id: Long?= null,
//
//        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
//        val building: Building? = null,
//
//        @Temporal(TemporalType.DATE)
//        val date: Date? = null,
//        val price: String? = null
//) : Serializable