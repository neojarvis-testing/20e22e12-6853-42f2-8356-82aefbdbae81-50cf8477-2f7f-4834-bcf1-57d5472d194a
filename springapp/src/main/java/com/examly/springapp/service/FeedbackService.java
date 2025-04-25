package com.examly.springapp.service;
import java.util.List;

import com.examly.springapp.model.Feedback;

public interface FeedbackService {
    public Feedback createFeedback(Feedback feedback);
    public List<Feedback> getAllFeedback();
    public List<Feedback> getFeedbackByUserId(int userId);
    public boolean deleteFeedback(Long id);
    public Feedback getFeedbackById(Long feedbackId);

}
    
