package com.avinash.jobapp.job.impl;

import com.avinash.jobapp.job.Job;
import com.avinash.jobapp.job.JobRepository;
import com.avinash.jobapp.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Override
    public List<Job> findALl() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
      jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
       Optional<Job> job=jobRepository.findById(id);
       return job.orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
        jobRepository.deleteById(id);
        return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Job updatedJob, Long id) {

        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setTitle(updatedJob.getTitle());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            jobRepository.save(job);
            return true;
        }

        return false;
    }
}
