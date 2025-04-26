package com.examly.springapp.service;
 
import java.util.List;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.FeedbackListEmptyException;
import com.examly.springapp.exception.FeedbackNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.mapper.FeedbackMapper;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.FeedbackDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;
 
@Service
public class FeedbackServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);
 
    @Autowired
    FeedbackRepo feedbackRepo;
 
    @Autowired
    UserRepo userRepo;
 
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        logger.info("Creating feedback for user ID: {}", feedbackDTO.getUserId());
        User existingUser = userRepo.findById(feedbackDTO.getUserId()).orElse(null);
        Feedback feedback = FeedbackMapper.mapFeedbackDTOToFeedbac(feedbackDTO, existingUser);
        if (existingUser == null) {
            logger.error("User not found with ID: {}", feedbackDTO.getUserId());
            throw new UserNotFoundException("User Not found");
        }
        feedback.setUser(existingUser);
        feedback = feedbackRepo.save(feedback);
        logger.info("Feedback successfully created with ID: {}", feedback.getFeedbackId());
        return FeedbackMapper.mapFeedbackToFeedbackDTO(feedback);
    }
 
    public List<FeedbackDTO> getAllFeedback() {
        logger.info("Fetching all feedback entries");
        List<Feedback> feedbackList = feedbackRepo.findAll();
        if (feedbackList.isEmpty()) {
            logger.error("No feedbacks available");
            throw new FeedbackListEmptyException("No Feedbacks are there");
        }
        return feedbackList.stream().map(feedback -> FeedbackMapper.mapFeedbackToFeedbackDTO(feedback)).toList();
    }
 
    public List<FeedbackDTO> getFeedbackByUserId(int userId) {
        logger.info("Fetching feedback for user ID: {}", userId);
        List<Feedback> feedbackList = feedbackRepo.findAllUserById(userId);
        if (feedbackList == null) {
            logger.error("Feedback list is empty for user ID: {}", userId);
            throw new FeedbackListEmptyException("Feedback list is empty for the user");
        }
        return feedbackList.stream().map(feedback -> FeedbackMapper.mapFeedbackToFeedbackDTO(feedback)).toList();
    }
 
    public boolean deleteFeedback(Long id) {
        logger.info("Deleting feedback with ID: {}", id);
        Feedback feedback = feedbackRepo.findById(id).orElse(null);
        if (feedback == null) {
            logger.error("Feedback not found with ID: {}", id);
            return false;
        } else {
            feedbackRepo.deleteById(id);
            logger.info("Feedback successfully deleted with ID: {}", id);
            return true;
        }
    }
 
    public FeedbackDTO getFeedbackById(Long feedbackId) {
        logger.info("Fetching feedback by ID: {}", feedbackId);
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        if (feedback == null) {
            logger.error("Feedback not found with ID: {}", feedbackId);
            throw new FeedbackNotFoundException("Feedback with ID: " + feedbackId + " not found");
        } else {
            return FeedbackMapper.mapFeedbackToFeedbackDTO(feedback);
        }
    }
}
