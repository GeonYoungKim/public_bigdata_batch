package com.skuniv.bigdata.domain.dto.open_api;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "header")
@XmlAccessorType(XmlAccessType.FIELD)
public class HeaderDto {
    @XmlElement(name = "resultCode")
    private int resultCode;

    @XmlElement(name = "resultMsg")
    private String resultMsg;
}
