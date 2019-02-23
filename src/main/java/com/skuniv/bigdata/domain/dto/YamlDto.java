package com.skuniv.bigdata.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@Data
public class YamlDto {

    @Value("${filePath}")
    private String filePath;

    @Value("${serviceKey}")
    private String serviceKey;

    @Value("${googleKey}")
    private String googleKey;
}
