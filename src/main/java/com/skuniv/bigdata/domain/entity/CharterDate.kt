//package com.skuniv.bigdata.domain.entity
//
//import java.io.Serializable
//import java.util.*
//import javax.persistence.*
//
//@Entity
//data class CharterDate(
//        @Id
//        @GeneratedValue(strategy = GenerationType.AUTO)
//        val id: Long? = null,
//
//        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
//        val building: Building? = null,
//
//        @Temporal(TemporalType.DATE)
//        val date: Date? = null,
//        val price: String? = null
//) : Serializable