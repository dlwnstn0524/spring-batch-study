package com.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping("/launchjob")
    public String launchJob(@RequestParam("requestDate") String requestDate) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("requestDate", requestDate)
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
            return "Job launched successfully!";
        } catch (Exception e){
            e.printStackTrace();
            return "Job failed to launch!";
        }
    }
}
