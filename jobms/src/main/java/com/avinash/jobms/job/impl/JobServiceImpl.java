package com.avinash.jobms.job.impl;


import com.avinash.jobms.job.Job;
import com.avinash.jobms.job.JobRepository;
import com.avinash.jobms.job.JobService;
import com.avinash.jobms.dto.JobDTO;
import com.avinash.jobms.job.clients.CompanyClient;
import com.avinash.jobms.job.clients.ReviewClient;
import com.avinash.jobms.job.external.Company;
import com.avinash.jobms.job.external.Review;
import com.avinash.jobms.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    CompanyClient companyClient;

    @Autowired
    ReviewClient reviewClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    JobRepository jobRepository;

    @Override
    @CircuitBreaker(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
//    @Retry(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findALl() {
        List<Job> jobs =jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).toList();
    }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("this is an alternative in case of circuit breaker");
        return list;
    }
    private JobDTO convertToDto(Job job){
//        Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/"+job.getCompanyId(),Company.class);
        Company company = companyClient.getCompany(job.getCompanyId());

//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {});

        List<Review> reviews = reviewClient.getReviewsForCompany(job.getCompanyId());
        return JobMapper.mapToJobWithCompanyDto(job,company,reviews);
    }
    @Override
    public void createJob(Job job) {
      jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        JobDTO jobWithCompanyDTO = new JobDTO();
        Job job = jobRepository.findById(id).orElse(null);
        if(job==null)return null;
       return convertToDto(job);
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
