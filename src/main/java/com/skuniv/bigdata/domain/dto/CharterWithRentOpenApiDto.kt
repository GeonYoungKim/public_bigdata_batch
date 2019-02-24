package com.skuniv.bigdata.domain.dto

import com.skuniv.bigdata.domain.dto.open_api.BuildingDealDto
import com.skuniv.bigdata.domain.dto.open_api.CharterWithRentBodyDto
import com.skuniv.bigdata.domain.dto.open_api.HeaderDto
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "response")
class CharterWithRentOpenApiDto(
        @XmlElement(name = "body")
        var body: CharterWithRentBodyDto? = null,
        @XmlElement(name = "header")
        var header: HeaderDto? = null
) : BuildingDealDto()