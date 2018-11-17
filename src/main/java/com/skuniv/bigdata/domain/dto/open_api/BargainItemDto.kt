package com.skuniv.bigdata.domain.dto.open_api

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement


@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
class BargainItemDto(

        @field:XmlElement(name = "거래금액")
        var dealPrice: String? = null,

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
        var regionCode: Int = 0,

        @field:XmlElement(name = "층")
        var floor: Int = 0
)