package com.skuniv.bigdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
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
        Job job1 = (Job) configurableApplicationContext.getBean(job.get(0));
        log.warn("job1 => {}", job1.getName());
        JobParametersBuilder builder = new JobParametersBuilder();
        jobLauncher.run(job1, builder.toJobParameters());
    }
}
