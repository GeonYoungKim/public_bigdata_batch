package com.skuniv.bigdata;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BigDataApplication implements ApplicationRunner {
    private static ConfigurableApplicationContext ctx;
    public static void main(String[] args) {
        ctx = SpringApplication.run(BigDataApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> job = args.getOptionValues("job");
        JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
        Job job1 = (Job) ctx.getBean(job.get(0));
        jobLauncher.run(job1,new JobParameters());
    }
}
