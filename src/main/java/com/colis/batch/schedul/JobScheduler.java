package com.colis.batch.schedul;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class JobScheduler {
    private final JobLauncher jobLauncher;

    private final ApplicationContext context;



//    @Scheduled(cron = "*/30 * * * * *")
    @Scheduled(cron = "* 2 0 * * *")
    public void DesactivePostProc() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        log.info(" ------------  cron activer -------------");
        var jobToStart = context.getBean("job1", Job.class);
        jobLauncher.run(jobToStart, new JobParameters());
    }


}
