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

    @Autowired
    FeedbackRepo feedbackRepo;

    @Autowired
    UserRepo userRepo;

    public Feedback createFeedback(Feedback feedback) {
        User existingUser=userRepo.findById(feedback.getUser().getUserId()).orElse(null);
        if(existingUser==null){
            return null;
        }
        feedback.setUser(existingUser);
        return feedbackRepo.save(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }

    public List<Feedback> getFeedbackByUserId(int userId) {
        return feedbackRepo.findAllUserById(userId);
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
