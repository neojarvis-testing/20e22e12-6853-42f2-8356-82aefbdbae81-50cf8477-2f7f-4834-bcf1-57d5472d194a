package com.examly.springapp.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FeedbackDTO {

    @NotNull(message = "User ID is required")
    private int userId;

    @NotBlank(message = "Message is required")
    private String message;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public FeedbackDTO(@NotNull(message = "User ID is required") int userId,
            @NotBlank(message = "Message is required") String message,
            @Min(value = 1, message = "Rating must be at least 1") @Max(value = 5, message = "Rating must be at most 5") int rating) {
        this.userId = userId;
        this.message = message;
        this.rating = rating;
    }

    public FeedbackDTO() {
    }
}