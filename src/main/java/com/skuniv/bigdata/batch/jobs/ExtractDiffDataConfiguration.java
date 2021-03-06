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
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

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
    private final DataSource dataSource;

    @Bean
    public Job extractDiffDataJob() {
        return jobBuilderFactory.get("extractDiffDataJob")
                .start(extractDiffDataPartitionStep())
                .build();
    }

    @Bean
    public Step extractDiffDataPartitionStep()
            throws UnexpectedInputException, ParseException {
        return stepBuilderFactory.get("extractDiffDataPartitionStep")
                .partitioner("extractDiffDataPartitionStep", openApiPartitioner)
                .step(extractDiffDataTrtStep())
                .build();
    }

    @Bean
    @Transactional
    public Step extractDiffDataTrtStep() {
        return stepBuilderFactory.get("extractDiffDataTrtStep")
                .transactionManager(jpaTransactionManager())
                .tasklet(extractDiffDataTasklet)
                .build();
    }

    @Bean
    @Primary
    public JpaTransactionManager jpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
