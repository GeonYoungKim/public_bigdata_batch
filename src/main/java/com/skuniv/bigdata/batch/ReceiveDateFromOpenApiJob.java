package com.skuniv.bigdata.batch;


import com.skuniv.bigdata.batch.open_api.OpenApiPartitioner;
import com.skuniv.bigdata.batch.open_api.OpenApiProcessor;
import com.skuniv.bigdata.batch.open_api.OpenApiReader;
import com.skuniv.bigdata.batch.open_api.OpenApiWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ReceiveDateFromOpenApiJob {

    private final OpenApiReader openApiReader;
    private final OpenApiProcessor openApiProcessor;
    private final OpenApiWriter openApiWriter;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() throws MalformedURLException {

        return jobBuilderFactory.get("receiveDateFromOpenApiJob15")
                .start(partitionStep())
                .build();
    }

    @Bean
    public Step partitionStep()
            throws UnexpectedInputException, MalformedURLException, ParseException {
        return stepBuilderFactory.get("partitionStep")
                .partitioner("slaveStep", partitioner())
                .step(slaveStep())
                .build();
    }


    @Bean
    public Step slaveStep()
            throws UnexpectedInputException, MalformedURLException, ParseException {
        return stepBuilderFactory.get("slaveStep").<String, String>chunk(6)
                .reader(openApiReader)
                .processor(openApiProcessor)
                .writer(openApiWriter)
                .build();
    }

    @Bean
    public OpenApiPartitioner partitioner() {
        OpenApiPartitioner partitioner = new OpenApiPartitioner();
        return partitioner;
    }
}