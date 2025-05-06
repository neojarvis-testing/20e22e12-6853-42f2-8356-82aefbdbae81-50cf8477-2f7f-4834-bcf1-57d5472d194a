package com.examly.springapp.service;
import java.util.*;
import com.examly.springapp.model.FeedbackDTO;

public interface FeedbackService {
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);
    public List<FeedbackDTO> getAllFeedback();
    public List<FeedbackDTO> getFeedbackByUserId(int userId);
    public Map<String,String> deleteFeedback(Long id);
    public FeedbackDTO getFeedbackById(Long feedbackId);
    public List<FeedbackDTO> getFeedbacksByPagination(Integer pageNo, Integer pageSize, String sortBy, String sortDir); 
}
    
