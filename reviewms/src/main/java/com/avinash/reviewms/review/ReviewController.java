package com.avinash.reviewms.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,@RequestBody Review review){
      if(reviewService.addReview(companyId,review)) {
          return new ResponseEntity<>("Review Added Successfully!!",HttpStatus.CREATED);
      }
      return new ResponseEntity<>("Review Not Saved, Company not found !!",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
      Review review= reviewService.getReview(reviewId);
      if(review!=null)return new ResponseEntity<>(review,HttpStatus.OK);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(reviewId,review);
        if(isReviewUpdated) {
            return new ResponseEntity<>("Review Updated Successfully!!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Updated!!",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not deleted!!",HttpStatus.NOT_FOUND);
    }
}
