package com.skuniv.bigdata.domain.dto.open_api;

import lombok.Data;

@Data
public abstract class BuildingDealDto {
    private String buildingType;
    private String dealType;
    private String apiKind;
}
