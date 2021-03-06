package com.skuniv.bigdata.domain.dto.open_api;

import lombok.*;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharterWithRentItemDto {
    @XmlElement(name = "보증금액")
    private String guaranteePrice;

    @XmlElement(name = "월세금액")
    private String monthlyPrice;

    @XmlElement(name = "건축년도")
    private int constructYear;

    @XmlElement(name = "년")
    private int year;

    @XmlElement(name = "법정동")
    private String dong;

    @XmlElements({
            @XmlElement(name = "아파트"),
            @XmlElement(name = "연립다세대"),
            @XmlElement(name = "단지")
    })
    private String name;

    @XmlElement(name = "월")
    private int monthly;

    @XmlElement(name = "일")
    private String days;

    @XmlElement(name = "전용면적")
    private Double area;

    @XmlElement(name = "지번")
    private String buildingNum;

    @XmlElement(name = "지역코드")
    private String regionCode;

    @XmlElement(name = "층")
    private int floor;
}
