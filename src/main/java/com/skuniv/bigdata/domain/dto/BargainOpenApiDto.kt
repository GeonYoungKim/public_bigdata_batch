package com.skuniv.bigdata.domain.dto


import com.skuniv.bigdata.domain.dto.open_api.BargainBodyDto
import com.skuniv.bigdata.domain.dto.open_api.BuildingDealDto
import com.skuniv.bigdata.domain.dto.open_api.HeaderDto
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "response")
class BargainOpenApiDto(
        @XmlElement(name = "body")
        var body: BargainBodyDto? = null,
        @XmlElement(name = "header")
        var header: HeaderDto? = null
): BuildingDealDto()