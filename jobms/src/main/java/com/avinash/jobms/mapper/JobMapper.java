package com.avinash.jobms.mapper;

import com.avinash.jobms.dto.CompanyWithReviewDTO;
import com.avinash.jobms.dto.JobDTO;
import com.avinash.jobms.job.Job;
import com.avinash.jobms.job.external.Company;
import com.avinash.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(Job job, Company company, List<Review> reviews){
        JobDTO jobWithCompanyDTO = new JobDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());

        CompanyWithReviewDTO companyWithReviewDTO = new CompanyWithReviewDTO();
        companyWithReviewDTO.setCompanyId(company.getId());
        companyWithReviewDTO.setName(company.getName());
        companyWithReviewDTO.setDescription(company.getDescription());
        companyWithReviewDTO.setReviews(reviews);

        jobWithCompanyDTO.setCompany(companyWithReviewDTO);
        return jobWithCompanyDTO;
    }
}
