package com.skuniv.bigdata.domain.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
class BargainDate(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        var building: Building? = null,

        @Temporal(TemporalType.DATE)
        var date: Date? = null,
        var price: String? = null
) : Serializable {
    private constructor(builder: Builder) : this(builder.id, builder.building, builder.date, builder.price)

    class Builder {
        var id: Long? = null
            private set
        var building: Building? = null
            private set
        var date: Date? = null
            private set
        var price: String? = null
            private set

    fun id(id: Long) = apply { this.id = id }
    fun building(Building: Building) = apply { this.building = building }
    fun date(date: Date) = apply { this.date = date }
    fun price(price: String) = apply { this.price = price }
    fun build() = BargainDate(this)
    }
}