package com.skuniv.bigdata.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import javax.persistence.*

@Entity
data class Building(
        @Id
        @GeneratedValue
        var no: Long? = null,
        var city: Int?,
        var groop: Int?,
        var dong: String?,
        var name: String?,
        var area: Double?,
        var floor: Int?,
        var type: Int?,
        var latitude: String?,
        var longitude: String?,
        @Column(name = "building_num")
        var buildingNum: String?,
        @Column(name = "construct_year")
        var constructYear: String?,
        @OneToMany(mappedBy = "building", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @JsonIgnore
        var bargainDates: Set<BargainDate>? = HashSet<BargainDate>(),
        @OneToMany(mappedBy = "building", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @JsonIgnore
        var charterDates: Set<CharterDate>? = HashSet<CharterDate>(),
        @OneToMany(mappedBy = "building", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @JsonIgnore
        var rentDates: Set<RentDate>? = HashSet<RentDate>()
) : Serializable {
    private constructor(builder: Builder) : this(builder.no, builder.city, builder.groop, builder.dong, builder.name, builder.area, builder.floor, builder.type, builder.latitude, builder.longitude, builder.buildingNum, builder.constructYear, builder.bargainDates, builder.charterDates, builder.rentDates)

    class Builder {
        var no: Long? = null
            private set
        var city: Int? = null
            private set
        var groop: Int? = null
            private set
        var dong: String? = null
            private set
        var name: String? = null
            private set
        var area: Double? = null
            private set
        var floor: Int? = null
            private set
        var type: Int? = null
            private set
        var latitude: String? = null
            private set
        var longitude: String? = null
            private set
        var buildingNum: String? = null
            private set
        var constructYear: String? = null
            private set
        var bargainDates: Set<BargainDate>? = null
            private set
        var charterDates: Set<CharterDate>? = null
            private set
        var rentDates: Set<RentDate>? = null
            private set

        fun city(city: Int) = apply { this.city = city }
        fun groop(groop: Int) = apply { this.groop = groop }
        fun dong(dong: String) = apply { this.dong = dong }
        fun name(name: String) = apply { this.name = name }
        fun area(area: Double) = apply { this.area = area }
        fun type(type: Int) = apply { this.type = type }
        fun latitude(latitude: String) = apply { this.latitude = latitude }
        fun longitude(longitude: String) = apply { this.longitude = longitude }
        fun floor(floor: Int) = apply { this.floor = floor }
        fun buildingNum(buildingNum: String) = apply { this.buildingNum = buildingNum }
        fun constructYear(constructYear: String) = apply { this.constructYear = constructYear }
        fun bargainDates(bargainDates: Set<BargainDate>) = apply { this.bargainDates = bargainDates }
        fun charterDates(charterDates: Set<CharterDate>) = apply { this.charterDates = charterDates }
        fun rentDates(rentDates: Set<RentDate>) = apply { this.rentDates = rentDates }
        fun build() = Building(this)
    }
}