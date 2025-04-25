package com.examly.springapp.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.mapper.FeedbackMapper;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.FeedbackDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;
 
@Service
public class FeedbackServiceImpl {
 
    @Autowired
    FeedbackRepo feedbackRepo;
 
    @Autowired
    UserRepo userRepo;
 
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        User existingUser=userRepo.findById(feedbackDTO.getUserId()).orElse(null);
        Feedback feedback=FeedbackMapper.mapFeedbackDTOToFeedbac(feedbackDTO, existingUser);
        if(existingUser==null){
            return null;
        }
        feedback.setUser(existingUser);
        feedback=feedbackRepo.save(feedback);
        return FeedbackMapper.mapFeedbackToFeedbackDTO(feedback);
    }
 
    public List<FeedbackDTO> getAllFeedback() {
        return feedbackRepo.findAll().stream().map(feedback->FeedbackMapper.mapFeedbackToFeedbackDTO(feedback)).toList();
    }
 
    public List<FeedbackDTO> getFeedbackByUserId(int userId) {
        return feedbackRepo.findAllUserById(userId).stream().map(feedback->FeedbackMapper.mapFeedbackToFeedbackDTO(feedback)).toList();
    }
 
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
 
    public FeedbackDTO getFeedbackById(Long feedbackId) {
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        if(feedback==null){
            return null;
        }
        else{
            return FeedbackMapper.mapFeedbackToFeedbackDTO(feedback);
        }
    }
}
 