package com.examly.springapp.mapper;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.FeedbackDTO;
import com.examly.springapp.model.User;

public class FeedbackMapper {

    public static Feedback mapFeedbackDTOToFeedbac(FeedbackDTO feedbackDTO,User user){
        return new Feedback(
            user,
            feedbackDTO.getMessage(),
            feedbackDTO.getRating()
        );
    }
    
    public static FeedbackDTO mapFeedbackToFeedbackDTO(Feedback feedback){
        return new FeedbackDTO(
            feedback.getId(),
            feedback.getUser().getId(),
            feedback.getMessage(),
            feedback.getRating()
        );
    }
}
