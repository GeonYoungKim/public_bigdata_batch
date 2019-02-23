package com.skuniv.bigdata.domain.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
data class RentDate(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        var buildingNo: Long? = null,

        @Temporal(TemporalType.DATE)
        var date: Date? = null,
        @Column(name = "guarantee_price")
        var guaranteePrice: String? = null,
        @Column(name = "monthly_price")
        var monthlyPrice: String? = null
) : Serializable {
    private constructor(builder: Builder) : this(builder.id, builder.buildingNo, builder.date, builder.guaranteePrice, builder.monthlyPrice)

    class Builder {
        var id: Long? = null
            private set
        var buildingNo: Long? = null
            private set
        var date: Date? = null
            private set
        var guaranteePrice: String? = null
            private set
        var monthlyPrice: String? = null
            private set

        fun id(id: Long) = apply { this.id = id }
        fun buildingNo(buildingNo: Long) = apply { this.buildingNo = buildingNo }
        fun date(date: Date) = apply { this.date = date }
        fun guaranteePrice(guaranteePrice: String) = apply { this.guaranteePrice = guaranteePrice }
        fun monthlyPrice(monthlyPrice: String) = apply { this.monthlyPrice = monthlyPrice }
        fun build() = RentDate(this)
    }
}