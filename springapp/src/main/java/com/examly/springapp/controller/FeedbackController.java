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
import com.examly.springapp.service.FeedbackServiceImpl;
@RestController
public class FeedbackController {
    private final FeedbackServiceImpl feedbackService;
 
   /**
     * Constructor-based injection ensures that FeedbackServiceImpl is properly initialized.
     * Constructor injection improves testability and ensures dependencies are injected at object creation.
     *
     * @param feedbackService Injected instance of FeedbackServiceImpl
     */

    public FeedbackController(FeedbackServiceImpl feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * Handles creation of a new feedback entry.
     * @param feedback The feedback details provided in the request body
     * @return ResponseEntity containing the newly created feedback with HTTP status 201 (Created)
     */
 



    @PostMapping("/api/feedback")
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO){
        return ResponseEntity.status(201).body(feedbackService.createFeedback(feedbackDTO));
    }

     /**
     * Retrieves all feedback entries stored in the system.
     * @return ResponseEntity containing a list of all feedback entries with HTTP status 200 (OK)
     */

    @GetMapping("/api/feedback")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedback(){
        return ResponseEntity.status(200).body(feedbackService.getAllFeedback());
    }
      /**
     * Retrieves feedback entries submitted by a specific user.
     * @param userId Unique identifier of the user
     * @return ResponseEntity containing a list of feedback entries for the user with HTTP status 200 (OK)
     */
 
    @GetMapping("/api/feedback/user/{userId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByUserId(@PathVariable int userId){
        return ResponseEntity.status(200).body(feedbackService.getFeedbackByUserId(userId));
    }
     /**
     * Deletes a feedback entry based on its unique ID.
     * @param id Unique identifier of the feedback entry to be deleted
     * @return ResponseEntity with appropriate status: 204 if deletion is successful, 404 if feedback is not found
     */
 
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
        
      /**
     * Retrieves a specific feedback entry by its unique ID.
     * @param feedbackId Unique identifier of the feedback entry
     * @return ResponseEntity containing the requested feedback with HTTP status 200 (OK)
     */   
 
    @GetMapping("/api/feedback/{feedbackId}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long feedbackId){
        return ResponseEntity.status(200).body(feedbackService.getFeedbackById(feedbackId));
    }
 
}