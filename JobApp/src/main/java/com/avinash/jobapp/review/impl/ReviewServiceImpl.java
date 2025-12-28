package com.avinash.jobapp.review.impl;

import com.avinash.jobapp.company.Company;
import com.avinash.jobapp.company.CompanyService;
import com.avinash.jobapp.review.Review;
import com.avinash.jobapp.review.ReviewRepository;
import com.avinash.jobapp.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
//        List<Review> reviews = reviewRepository.findAll().stream()
//                .filter(review -> review.getCompany().getId().equals(companyId)).toList();

        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Optional<Company> company = companyService.getCompanyById(companyId);
        if(company.isPresent()){
            review.setCompany(company.get());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        /*
//      Review review = reviewRepository.findReviewByReviewId(companyId,reviewId);
         ---> We can create custom function in Repository by using JPA Query
        */

        Review review = reviewRepository.findAllByCompanyId(companyId).stream()
                .filter(review1 -> review1.getId().equals(reviewId)).findFirst().orElse(null);

      return review;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        Review reviewToUpdate = reviewRepository.findAllByCompanyId(companyId)
                .stream().filter(rev -> rev.getId().equals(reviewId)).findFirst().orElse(null);
        if(reviewToUpdate!=null){
            reviewToUpdate.setTitle(review.getTitle());
            reviewToUpdate.setDescription(review.getDescription());
            reviewToUpdate.setRating(review.getRating());
            reviewRepository.save(reviewToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        for(Review review: reviewRepository.findAllByCompanyId(companyId)){
            if(review.getId().equals(reviewId)){
                reviewRepository.deleteById(reviewId);
                return true;
            }
        }
        return false;
    }
}
