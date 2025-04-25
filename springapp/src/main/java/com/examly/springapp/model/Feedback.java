package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents a feedback entry in the system.
 * Feedback is associated with a user and contains a message and rating.
 */

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    private String message;
    private int rating;

    // Getter and Setter methods to access and modify feedback details

    public Long getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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

    //Default constructor.

    public Feedback() {
    }

     /**
     * Parameterized constructor for initializing a feedback entry.
     *
     * @param feedbackId Unique identifier for feedback
     * @param user Associated user who provided feedback
     * @param message Text message containing feedback content
     * @param rating Numeric rating assigned to feedback (e.g., 1 to 5)
     */
    
    public Feedback(Long feedbackId, User user, String message, int rating) {
        this.feedbackId = feedbackId;
        this.user = user;
        this.message = message;
        this.rating = rating;
    }
    
    



}
