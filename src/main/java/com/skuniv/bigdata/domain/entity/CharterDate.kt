package com.skuniv.bigdata.domain.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
data class CharterDate(
        @Id
        @ManyToOne
        val building: Building? = null,
        @Id
        @Temporal(TemporalType.DATE)
        val date: Date? = null,
        val price: String? = null
) : Serializable