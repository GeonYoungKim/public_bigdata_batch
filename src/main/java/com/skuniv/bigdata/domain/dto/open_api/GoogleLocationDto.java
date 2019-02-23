package com.skuniv.bigdata.domain.dto.open_api;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class GoogleLocationDto {
    @NonNull
    private String latitude;
    @NonNull
    private String longitude;
}
