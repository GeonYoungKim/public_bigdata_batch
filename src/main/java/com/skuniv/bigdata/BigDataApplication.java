package com.skuniv.bigdata;

import com.skuniv.bigdata.domain.dto.BargainOpenApiDto;
import com.skuniv.bigdata.domain.dto.open_api.BargainItemDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BigDataApplication {
    public static void main(String[] args) {
//        SpringApplication.run(BigDataApplication.class, args);
    }
}
