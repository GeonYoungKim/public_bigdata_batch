package com.skuniv.bigdata.domain.dto.open_api;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class BargainBodyDto {
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<BargainItemDto> item;

    @XmlElement(name = "numOfRows")
    private int numOfRows;

    @XmlElement(name = "pageNo")
    private int pageNo;

    @XmlElement(name = "totalCount")
    private int totalCount;
}
