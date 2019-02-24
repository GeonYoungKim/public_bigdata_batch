package com.skuniv.bigdata.domain.dto.open_api;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
public class GoogleLocationDto {
    @NonNull
    private String latitude;
    @NonNull
    private String longitude;
}
