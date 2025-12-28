package com.avinash.jobapp.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review){
      if(reviewService.addReview(companyId,review)) {
          return new ResponseEntity<>("Review Added Successfully!!",HttpStatus.CREATED);
      }
      return new ResponseEntity<>("Review Not Saved, Company not found !!",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,@PathVariable Long reviewId){
      Review review= reviewService.getReview(companyId,reviewId);
      if(review!=null)return new ResponseEntity<>(review,HttpStatus.OK);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(companyId,reviewId,review);
        if(isReviewUpdated) {
            return new ResponseEntity<>("Review Updated Successfully!!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Updated!!",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(companyId,reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not deleted!!",HttpStatus.NOT_FOUND);
    }
}
