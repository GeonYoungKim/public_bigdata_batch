package com.skuniv.bigdata.batch.jobs;

import com.skuniv.bigdata.batch.items.ExtractDiffDataTasklet;
import com.skuniv.bigdata.batch.items.OpenApiPartitioner;
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
@RequiredArgsConstructor
@EnableBatchProcessing
@Import({OpenApiPartitioner.class, ExtractDiffDataTasklet.class})
public class ExtractDiffDataConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final OpenApiPartitioner openApiPartitioner;
    private final ExtractDiffDataTasklet extractDiffDataTasklet;

    @Bean
    public Job extractDiffDataJob() throws MalformedURLException {
        return jobBuilderFactory.get("apiCallJob")
                .start(extractDiffDataPartitionStep())
                .build();
    }

    @Bean
    public Step extractDiffDataPartitionStep()
            throws UnexpectedInputException, MalformedURLException, ParseException {
        return stepBuilderFactory.get("apiCallPartitionStep")
                .partitioner("slaveStep", openApiPartitioner).partitionHandler(extractDiffDataPartitionHandler())
                .step(extractDiffDataTrtStep())
                .build();
    }

    @Bean
    public Step extractDiffDataTrtStep() {
        return stepBuilderFactory.get("extractDiffDataTrtStep")
                .tasklet(extractDiffDataTasklet)
                .build();
    }

    @Bean
    public PartitionHandler extractDiffDataPartitionHandler() {
        TaskExecutorPartitionHandler taskExecutorPartitionHandler = new TaskExecutorPartitionHandler();
        taskExecutorPartitionHandler.setGridSize(300);
        taskExecutorPartitionHandler.setTaskExecutor(extractDiffDataThreadPoolExecutor());
        taskExecutorPartitionHandler.setStep(extractDiffDataTrtStep());
        return taskExecutorPartitionHandler;
    }

    @Bean
    public ThreadPoolTaskExecutor extractDiffDataThreadPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(300);
        return threadPoolTaskExecutor;
    }
}
