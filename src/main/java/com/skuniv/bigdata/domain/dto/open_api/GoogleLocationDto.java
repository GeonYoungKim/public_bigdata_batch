package com.skuniv.bigdata.domain.dto.open_api;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GoogleLocationDto {
    @NonNull
    private String latitude;
    @NonNull
    private String longitude;

    @Builder
    public GoogleLocationDto(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
