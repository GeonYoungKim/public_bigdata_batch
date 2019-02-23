package com.skuniv.bigdata.domain.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
data class BargainDate(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        @ManyToOne(cascade = arrayOf(CascadeType.ALL))
        var buildingNo: Long? = null,

        @Temporal(TemporalType.DATE)
        var date: Date? = null,
        var price: String? = null
) : Serializable {
    private constructor(builder: Builder) : this(builder.id, builder.buildingNo, builder.date, builder.price)

    class Builder {
        var id: Long? = null
            private set
        var buildingNo: Long? = null
            private set
        var date: Date? = null
            private set
        var price: String? = null
            private set

    fun id(id: Long) = apply { this.id = id }
    fun buildingNo(buildingNo: Long) = apply { this.buildingNo = buildingNo }
    fun date(date: Date) = apply { this.date = date }
    fun price(price: String) = apply { this.price = price }
    fun build() = BargainDate(this)
    }
}