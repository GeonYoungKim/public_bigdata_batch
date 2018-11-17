package com.skuniv.bigdata;

import com.google.gson.Gson;
import com.skuniv.bigdata.domain.dto.BargainOpenApiDto;
import com.skuniv.bigdata.domain.dto.CharterWithRentOpenApiDto;
import com.skuniv.bigdata.util.OpenApiConstants;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

@EnableBatchProcessing
@SpringBootApplication
public class BigdataApplication {
    public static void main(String[] args) throws UnsupportedEncodingException, URISyntaxException {
        Gson gson = new Gson();
        URI url = new URI("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent?LAWD_CD=11110&DEAL_YMD=201512" + OpenApiConstants.SERVER_KEY);

        RestTemplate restTemplate = new RestTemplate();
        CharterWithRentOpenApiDto bargainOpenApiDto = restTemplate.getForObject(url, CharterWithRentOpenApiDto.class);
        System.out.println(gson.toJson(bargainOpenApiDto));
        SpringApplication.run(BigdataApplication.class, args);
    }
}
