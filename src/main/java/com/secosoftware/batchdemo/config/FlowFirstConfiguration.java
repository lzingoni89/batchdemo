package com.secosoftware.batchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowFirstConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step firstStep() {
        return stepBuilderFactory.get("firstStep")
                .allowStartIfComplete(true)
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("--> FIRST STEP");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job flowFirstJob(Flow flow) {
        return jobBuilderFactory.get("flowFirstJob")
                .start(firstStep())
                .on("COMPLETED").to(flow)
                .end()
                .build();
    }

}
