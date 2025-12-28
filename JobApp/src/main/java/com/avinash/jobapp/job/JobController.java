package com.avinash.jobapp.job;

import com.avinash.jobapp.job.impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController{
    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
      return new ResponseEntity<>(jobService.findALl(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getJobById(@PathVariable Long id){

       Job job = jobService.getJobById(id);
       if(job!=null)return new ResponseEntity<>(job, HttpStatus.OK);
       return new ResponseEntity<>("Job with Id not found",HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
            return new ResponseEntity<>("Job Deleted Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Job not Found With id: "+id,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@RequestBody Job updatedJob,@PathVariable Long id){
        boolean updated = jobService.updateJob(updatedJob,id);
        if(updated)
            return new ResponseEntity<>("Job Updated Successfully!!",HttpStatus.OK);
        return new ResponseEntity<>("Job Not Found with Id :"+id,HttpStatus.NOT_FOUND);
    }
}
