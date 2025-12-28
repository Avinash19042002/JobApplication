package com.avinash.reviewms.review.impl;


import com.avinash.reviewms.review.Review;
import com.avinash.reviewms.review.ReviewRepository;
import com.avinash.reviewms.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews(Long companyId) {
//        List<Review> reviews = reviewRepository.findAll().stream()
//                .filter(review -> review.getCompany().getId().equals(companyId)).toList();

        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if(companyId!=null && review!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        /*
//      Review review = reviewRepository.findReviewByReviewId(companyId,reviewId);
         ---> We can create custom function in Repository by using JPA Query
        */

        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review review) {
//        Review reviewToUpdate = reviewRepository.findAllByCompanyId(companyId)
//                .stream().filter(rev -> rev.getId().equals(reviewId)).findFirst().orElse(null);

        Review reviewToUpdate = reviewRepository.findById(reviewId).orElse(null);
        if(reviewToUpdate!=null){
            reviewToUpdate.setTitle(review.getTitle());
            reviewToUpdate.setDescription(review.getDescription());
            reviewToUpdate.setRating(review.getRating());
            reviewToUpdate.setCompanyId(review.getCompanyId());
            reviewRepository.save(reviewToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
