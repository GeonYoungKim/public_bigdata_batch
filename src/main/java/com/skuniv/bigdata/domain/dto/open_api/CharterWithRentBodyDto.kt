package com.skuniv.bigdata.domain.dto.open_api

import javax.xml.bind.annotation.*

@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
data class CharterWithRentBodyDto(

        @XmlElementWrapper(name = "items")
        @XmlElement(name = "item")
        var item: List<CharterWithRentItemDto>? = null,

        @XmlElement(name = "numOfRows")
        var numOfRows: Int = 0,

        @XmlElement(name = "pageNo")
        var pageNo: Int = 0,

        @XmlElement(name = "totalCount")
        var totalCount: Int = 0
)