package com.skuniv.bigdata.domain.dto.open_api;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class BuildingDealDto {
    private String buildingType;
    private String dealType;
    private String apiKind;
}
