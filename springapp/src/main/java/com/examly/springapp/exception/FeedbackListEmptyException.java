package com.examly.springapp.exception;
 
public class FeedbackListEmptyException extends RuntimeException{
    public FeedbackListEmptyException(String msg){
        super(msg);
    }
}