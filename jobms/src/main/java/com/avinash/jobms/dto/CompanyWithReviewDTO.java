package com.avinash.jobms.dto;

import com.avinash.jobms.job.external.Review;

import java.util.List;

public class CompanyWithReviewDTO {
    private Long companyId;
    private String name;
    private String description;
    private List<Review> reviews;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long id) {
        this.companyId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
