package com.avinash.jobapp.job;

import org.springframework.stereotype.Service;

import java.util.List;


public interface JobService {
    List<Job> findALl();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Job updatedJob, Long id);
}
