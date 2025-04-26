package com.examly.springapp.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.FeedbackDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.FeedbackServiceImpl;
@RestController
public class FeedbackController {
    private final FeedbackServiceImpl feedbackService;
 
    // Constructor for injecting FeedbackServiceImpl
   
    public FeedbackController(FeedbackServiceImpl feedbackService) {
        this.feedbackService = feedbackService;
    }

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

    @GetMapping("/api/feedback")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedback(){
        return ResponseEntity.status(200).body(feedbackService.getAllFeedback());
    }
 
    @GetMapping("/api/feedback/user/{userId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByUserId(@PathVariable int userId){
        return ResponseEntity.status(200).body(feedbackService.getFeedbackByUserId(userId));
    }
 
    @DeleteMapping("/api/feedback/{id}")
    public ResponseEntity<String>deleteFeedback(@PathVariable Long id){
        boolean isDeleted = feedbackService.deleteFeedback(id);
        if(isDeleted){
            return ResponseEntity.status(204).body("Deletion successfull");
        }
        else{
            return ResponseEntity.status(404).body("Deletion unsuccessfull");
        }
        }
 
    @GetMapping("/api/feedback/{feedbackId}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long feedbackId){
        return ResponseEntity.status(200).body(feedbackService.getFeedbackById(feedbackId));
    }
 
}