package com.secosoftware.batchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .allowStartIfComplete(true)
                .tasklet( (stepContribution, chunkContext) -> {
                    System.out.println("--> THIS IS STEP 1");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .allowStartIfComplete(true)
                .tasklet( (stepContribution, chunkContext) -> {
                    System.out.println("--> THIS IS STEP 2");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .allowStartIfComplete(true)
                .tasklet( (stepContribution, chunkContext) -> {
                    System.out.println("--> THIS IS STEP 3");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory.get("transitionJobNext")
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

}
