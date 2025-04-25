package com.examly.springapp.exception;

public class FeedbackNotFoundException extends RuntimeException{
    public FeedbackNotFoundException(String msg){
        super(msg);
    }
}
