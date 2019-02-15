package com.skuniv.bigdata.batch.jobs;


import com.skuniv.bigdata.batch.items.OpenApiPartitioner;
import com.skuniv.bigdata.batch.items.OpenApiReader;
import com.skuniv.bigdata.batch.items.OpenApiWriter;
import com.skuniv.bigdata.domain.dto.open_api.BuildingDealDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.net.MalformedURLException;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@Import({OpenApiReader.class, OpenApiWriter.class, OpenApiPartitioner.class})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class FeedingOpenApiDataConfiguration {

    private final OpenApiReader openApiReader;
    private final OpenApiWriter openApiWriter;
    private final OpenApiPartitioner openApiPartitioner;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job apiCallJob() throws MalformedURLException {
        return jobBuilderFactory.get("apiCallJob")
                .start(apiCallPartitionStep())
                .build();
    }

    @Bean
    public Step apiCallPartitionStep()
            throws UnexpectedInputException, ParseException {
        return stepBuilderFactory.get("apiCallPartitionStep")
                .partitioner("apiCallPartitionStep", openApiPartitioner).partitionHandler(feedingApiDataPartitionHandler())
                .step(apiCallTrtStep())
                .build();
    }


    @Bean
    public Step apiCallTrtStep() {
        return stepBuilderFactory.get("apiCallTrtStep").<BuildingDealDto, BuildingDealDto>chunk(6)
                .reader(openApiReader)
                .writer(openApiWriter)
                .build();
    }

    @Bean
    public PartitionHandler feedingApiDataPartitionHandler() {
        TaskExecutorPartitionHandler taskExecutorPartitionHandler = new TaskExecutorPartitionHandler();
        taskExecutorPartitionHandler.setGridSize(300);
        taskExecutorPartitionHandler.setTaskExecutor(feedingApiDataThreadPoolExecutor());
        taskExecutorPartitionHandler.setStep(apiCallTrtStep());
        return taskExecutorPartitionHandler;
    }

    @Bean
    public ThreadPoolTaskExecutor feedingApiDataThreadPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(300);
        return threadPoolTaskExecutor;
    }
}