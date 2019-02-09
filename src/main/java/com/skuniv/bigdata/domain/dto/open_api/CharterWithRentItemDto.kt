package com.skuniv.bigdata.domain.dto.open_api

import lombok.Builder
import lombok.Data
import javax.xml.bind.annotation.*

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
data class CharterWithRentItemDto(
        @field:XmlElement(name = "보증금액")
        var guaranteePrice: String? = null,

        @field:XmlElement(name = "월세금액")
        var monthlyPrice: String? = null,

        @field:XmlElement(name = "건축년도")
        var constructYear: Int = 0,

        @field:XmlElement(name = "년")
        var year: Int = 0,

        @field:XmlElement(name = "법정동")
        var dong: String? = null,

        @field:XmlElement(name = "아파트")
        var name: String? = null,

        @field:XmlElement(name = "월")
        var monthly: Int = 0,

        @field:XmlElement(name = "일")
        var days: String? = null,

        @field:XmlElement(name = "전용면적")
        var area: Double? = null,

        @field:XmlElement(name = "지번")
        var buildingNum: String? = null,

        @field:XmlElement(name = "지역코드")
        var regionCode: String? = null,

        @field:XmlElement(name = "층")
        var floor: Int = 0
)