package com.colis.batch.config;

import com.colis.batch.tasklet.SendProcTasklet;
import com.colis.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class BatchConfig {

    private final PostService postService;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager batchTransactionManager;

    private final JobLauncher jobLauncher;

    @Bean
    public Step step(){
        return new StepBuilder("step", jobRepository)
                .tasklet(new SendProcTasklet(postService), batchTransactionManager)
                .build();
    }

    @Bean
    public Job job1(){
        return new JobBuilder("job1", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

}
