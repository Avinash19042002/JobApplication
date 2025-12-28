package com.avinash.jobapp.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByCompanyId(Long companyId);

    @Query("SELECT r FROM Review r WHERE r.id = ?2 AND r.company.id = ?1")
    Review findReviewByReviewId(Long companyId,Long reviewId);
}
