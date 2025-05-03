package com.examly.springapp.mapper;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.FeedbackDTO;
import com.examly.springapp.model.User;

public class FeedbackMapper {

    // Private constructor to prevent instantiation
    private FeedbackMapper() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // Convert FeedbackDTO into Feedback entity
    public static Feedback mapFeedbackDTOToFeedback(FeedbackDTO feedbackDTO, User user) {
        return new Feedback(
            user,
            feedbackDTO.getMessage(),
            feedbackDTO.getRating()
        );
    }

    // Convert Feedback entity into FeedbackDTO
    public static FeedbackDTO mapFeedbackToFeedbackDTO(Feedback feedback) {
        return new FeedbackDTO(
            feedback.getId(),
            feedback.getUser().getId(),
            feedback.getMessage(),
            feedback.getRating()
        );
    }
}