package com.skuniv.bigdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableJpaRepositories
public class BigDataApplication implements ApplicationRunner {
    @Autowired
    ConfigurableApplicationContext configurableApplicationContext;
    @Autowired
    private JobLauncher jobLauncher;

    public static void main(String[] args) {
        SpringApplication.run(BigDataApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> job = args.getOptionValues("job");
        log.warn("job => " + job.get(0));
        log.warn("ctx => " + configurableApplicationContext);
        Job executeJob = (Job) configurableApplicationContext.getBean(job.get(0));
        log.warn("job1 => {}", executeJob.getName());
        JobParametersBuilder builder = new JobParametersBuilder();
        jobLauncher.run(executeJob, builder.toJobParameters());
    }
}
