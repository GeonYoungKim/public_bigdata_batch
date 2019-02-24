package com.skuniv.bigdata.domain.dto.open_api;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@NoArgsConstructor
@XmlRootElement(name = "header")
@XmlAccessorType(XmlAccessType.FIELD)
public class HeaderDto {
    @XmlElement(name = "resultCode")
    private int resultCode;

    @XmlElement(name = "resultMsg")
    private String resultMsg;
}
