package com.examly.springapp.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;
 
@Service
public class FeedbackServiceImpl {
 
    private final FeedbackRepo feedbackRepo;
    private final UserRepo userRepo;

    /**
     * Constructor-based dependency injection for FeedbackRepo and UserRepo.
     * 
     * Constructor injection improves testability, ensures proper initialization,
     * and avoids field injection, which is harder to manage in unit tests.
     *
     * @param feedbackRepo Injected instance of FeedbackRepo for feedback operations
     * @param userRepo Injected instance of UserRepo for user operations
     */
    public FeedbackServiceImpl(FeedbackRepo feedbackRepo, UserRepo userRepo) {
        this.feedbackRepo = feedbackRepo;
        this.userRepo = userRepo;
    }
    /**
     * Creates a new feedback entry after validating that the associated user exists.
     *
     * @param feedback The feedback object containing user feedback details
     * @return The saved feedback entity if the user exists, otherwise returns null
     */
 
    public Feedback createFeedback(Feedback feedback) {
        User existingUser=userRepo.findById(feedback.getUser().getUserId()).orElse(null);
        if(existingUser==null){
            return null;
        }
        feedback.setUser(existingUser);
        return feedbackRepo.save(feedback);
    }
     /**
     * Retrieves all feedback entries from the database.
     *
     * @return List of all feedback records
     */
 
    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }
    /**
     * Retrieves all feedback records submitted by a specific user.
     *
     * @param userId Unique identifier of the user
     * @return List of feedback entries associated with the given user ID
     */
 
    public List<Feedback> getFeedbackByUserId(int userId) {
        return feedbackRepo.findAllUserById(userId);
    }
    /**
     * Deletes a feedback entry based on its ID.
     * Performs validation to ensure the feedback entry exists before deletion.
     *
     * @param id Unique identifier of the feedback entry
     * @return True if deletion is successful, false if the feedback entry was not found
     */
 
    public boolean deleteFeedback(Long id) {
        Feedback feedback = feedbackRepo.findById(id).orElse(null);
        if(feedback == null){
            return false;
        }
        else{
            feedbackRepo.deleteById(id);
            return true;
        }
    }
     /**
     * Retrieves a specific feedback entry by its unique ID.
     *
     * @param feedbackId Unique identifier of the feedback entry
     * @return Feedback entity if found, otherwise returns null
     */
 
    public Feedback getFeedbackById(Long feedbackId) {
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        if(feedback==null){
            return null;
        }
        else{
            return feedback;
        }
    } 
 
}
 
