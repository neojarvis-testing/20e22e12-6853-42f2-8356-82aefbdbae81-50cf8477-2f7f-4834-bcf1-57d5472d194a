package com.examly.springapp.service;
 
import java.util.List;
 
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
 
    @Autowired
    FeedbackRepo feedbackRepo;
 
    @Autowired
    UserRepo userRepo;
 
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        User existingUser=userRepo.findById(feedbackDTO.getUserId()).orElse(null);
        Feedback feedback=FeedbackMapper.mapFeedbackDTOToFeedbac(feedbackDTO, existingUser);
        if(existingUser==null){
            throw new UserNotFoundException("User Not found");
        }
        feedback.setUser(existingUser);
        feedback=feedbackRepo.save(feedback);
        return FeedbackMapper.mapFeedbackToFeedbackDTO(feedback);
    }
 
    public List<FeedbackDTO> getAllFeedback() {
        List<Feedback> feedbackList=feedbackRepo.findAll();
        if(feedbackList.isEmpty()){
            throw new FeedbackListEmptyException("No Feedbacks are there");
        }
        return feedbackList.stream().map(feedback->FeedbackMapper.mapFeedbackToFeedbackDTO(feedback)).toList();
    }
 
    public List<FeedbackDTO> getFeedbackByUserId(int userId) {
        List<Feedback> feedbackList=feedbackRepo.findAllUserById(userId);
        if(feedbackList==null){
            throw new FeedbackListEmptyException("Feedback list is empty for the user");
        }
        return feedbackList.stream().map(feedback->FeedbackMapper.mapFeedbackToFeedbackDTO(feedback)).toList();
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
            throw new FeedbackNotFoundException("Feedback with ID: "+feedbackId+" not found");
        }
        else{
            return FeedbackMapper.mapFeedbackToFeedbackDTO(feedback);
        }
    }
 
}
 