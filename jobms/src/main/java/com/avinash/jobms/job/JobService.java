package com.avinash.jobms.job;

import com.avinash.jobms.dto.JobDTO;

import java.util.List;


public interface JobService {
    List<JobDTO> findALl();
    void createJob(Job job);

    JobDTO getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Job updatedJob, Long id);
}
