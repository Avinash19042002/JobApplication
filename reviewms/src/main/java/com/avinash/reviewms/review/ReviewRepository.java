package com.avinash.reviewms.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByCompanyId(Long companyId);
}
