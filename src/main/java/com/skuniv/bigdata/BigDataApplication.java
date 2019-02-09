package com.skuniv.bigdata;

import com.skuniv.bigdata.domain.dto.BargainOpenApiDto;
import com.skuniv.bigdata.domain.dto.CharterWithRentOpenApiDto;
import com.skuniv.bigdata.domain.dto.open_api.BargainItemDto;
import com.skuniv.bigdata.domain.dto.open_api.CharterWithRentItemDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class BigDataApplication {
    public static void main(String[] args) throws URISyntaxException {
        URI url = new URI("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade?LAWD_CD=11110&DEAL_YMD=201512&serviceKey=%2BOYuTJYMUl8Rx17U1zJFP35LgxFd5b81a94LTqltyHekTu4WdmXxhaBJePS7Y1%2FppFV3k6aqdSl4DDG%2FfylR4w%3D%3D");

        RestTemplate restTemplate = new RestTemplate();
        BargainOpenApiDto bargainOpenApiDto = restTemplate.getForObject(url, BargainOpenApiDto.class);
        for(BargainItemDto item : bargainOpenApiDto.getBody().getItem()){
            System.out.println(item.toString());
        }
        SpringApplication.run(BigDataApplication.class, args);
    }
}
