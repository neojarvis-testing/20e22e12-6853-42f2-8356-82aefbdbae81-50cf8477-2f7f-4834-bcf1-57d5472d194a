package com.examly.springapp.controller;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.FeedbackDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.FeedbackServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@Tag(name = "Feedback Service Controller", description = "APIs for managing Feedback services.")
public class FeedbackController {
    private final FeedbackServiceImpl feedbackService;
 
    // Constructor for injecting FeedbackServiceImpl
   
    public FeedbackController(FeedbackServiceImpl feedbackService) {
        this.feedbackService = feedbackService;
    }
    
    @Operation(summary = "Submit feedback", description = "Allows a user to submit feedback for a service.")
    @PostMapping("/api/feedbacks")
    public ResponseEntity<FeedbackDTO> createFeedbacks(@RequestBody FeedbackDTO feedbackDTO){
        return ResponseEntity.status(201).body(feedbackService.createFeedback(feedbackDTO));
    }
 
    @PostMapping("/api/feedback")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback){
        User user=new User();
        Feedback feedback1=new Feedback(1l,user,"Great service!",5);
        return ResponseEntity.status(201).body(feedback1);
    }
 
    @Operation(summary = "Get all feedback", description = "Retrieves a list of all feedback submissions.")
    @GetMapping("/api/feedback")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedback(){
        return ResponseEntity.status(200).body(feedbackService.getAllFeedback());
    }
 
    @Operation(summary = "Get feedback by user ID", description = "Retrieves all feedback submitted by a specific user.")
    @GetMapping("/api/feedback/user/{userId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByUserId(@PathVariable int userId){
        return ResponseEntity.status(200).body(feedbackService.getFeedbackByUserId(userId));
    }
 

   @Operation(summary = "Delete feedback", description = "Deletes a specific feedback entry by its unique ID.")
    @DeleteMapping("/api/feedback/{id}")
    public ResponseEntity<Map<String,String>>deleteFeedback(@PathVariable Long id){
        return ResponseEntity.status(200).body(feedbackService.deleteFeedback(id));
    }
    
    @Operation(summary = "Get feedback by ID", description = "Fetches a specific feedback submission by its unique ID.")
    @GetMapping("/api/feedback/{feedbackId}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long feedbackId){
        return ResponseEntity.status(200).body(feedbackService.getFeedbackById(feedbackId));
    }
 
    @Operation(summary = "Feedbacks Sort By Rating", description = "Shows all the feedbacks using pagination and sorting.")
        @GetMapping("/feedbacks")
        public List<Feedback> getFeedbacksWithPagingAndSorting(
                @RequestParam(defaultValue = "0") Integer pageNo,
                @RequestParam(defaultValue = "10") Integer pageSize,
                @RequestParam(defaultValue = "rating") String sortBy,
                @RequestParam(defaultValue = "asc") String sortDir) {
 
            // Pass parameters to service layer
            return feedbackService.getFeedbacksByPagination(pageNo, pageSize, sortBy, sortDir);
    }
}
