package com.skuniv.bigdata.domain.dto.open_api

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "header")
@XmlAccessorType(XmlAccessType.FIELD)
data class HeaderDto(
        @XmlElement(name = "resultCode")
        var resultCode: Int = 0,
        @XmlElement(name = "resultMsg")
        val resultMsg: String? = null
)