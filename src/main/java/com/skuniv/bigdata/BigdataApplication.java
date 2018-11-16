package com.skuniv.bigdata;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@EnableBatchProcessing
@SpringBootApplication
public class BigdataApplication {
    public static void main(String[] args) {

        SpringApplication.run(BigdataApplication.class, args);
    }
}
