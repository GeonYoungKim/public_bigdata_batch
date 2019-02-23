package com.skuniv.bigdata.batch.jobs;

import com.skuniv.bigdata.batch.items.ExtractDiffDataTasklet;
import com.skuniv.bigdata.batch.items.OpenApiPartitioner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.MalformedURLException;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
@EnableJpaRepositories(basePackages = {"com.skuniv.bigdata.repository"})
@Import({OpenApiPartitioner.class, ExtractDiffDataTasklet.class})
public class ExtractDiffDataConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final OpenApiPartitioner openApiPartitioner;
    private final ExtractDiffDataTasklet extractDiffDataTasklet;

    @Bean
    public Job extractDiffDataJob() throws MalformedURLException {
        return jobBuilderFactory.get("extractDiffDataJob")
                .start(extractDiffDataPartitionStep())
                .build();
    }

    @Bean
    public Step extractDiffDataPartitionStep()
            throws UnexpectedInputException, ParseException {
        return stepBuilderFactory.get("extractDiffDataPartitionStep")
                .partitioner("slaveStep", openApiPartitioner)
                .step(extractDiffDataTrtStep())
                .build();
    }

    @Bean
    public Step extractDiffDataTrtStep() {
        return stepBuilderFactory.get("extractDiffDataTrtStep")
                .tasklet(extractDiffDataTasklet)
                .build();
    }
}
