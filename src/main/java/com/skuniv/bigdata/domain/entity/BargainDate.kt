package com.skuniv.bigdata.domain.entity

import java.io.Serializable
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class BargainDate(
        @Id
        @ManyToOne
        val building: Building? = null,
        @Id
        val date: Date? = null,
        val price: String? = null
) : Serializable