package com.examly.springapp.exception;
 
import org.springframework.web.bind.annotation.ControllerAdvice;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
 
import java.util.HashMap;
import java.util.Map;
 
@ControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
 
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(404).body(Map.of("message",ex.getMessage()));
    }
 
    @ExceptionHandler(VehicleMaintenanceServiceNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleVehicleMaintenanceServiceNotFoundException(VehicleMaintenanceServiceNotFoundException ex){
        return ResponseEntity.status(404).body(Map.of("message",ex.getMessage()));
    }
 
    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleFeedbackNotFoundException(FeedbackNotFoundException ex){
        return ResponseEntity.status(404).body(Map.of("message",ex.getMessage()));
    }
 
    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleAppointmentNotFoundException(AppointmentNotFoundException ex){
        return ResponseEntity.status(404).body(Map.of("message",ex.getMessage()));
    }
 
    @ExceptionHandler(VehicleMaintenanceListEmptyException.class)
    public ResponseEntity<Map<String,String>> handleVehicleMaintenanceListEmptyException(VehicleMaintenanceListEmptyException ex){
        return ResponseEntity.status(404).body(Map.of("message",ex.getMessage()));
    }
 
    @ExceptionHandler(FeedbackListEmptyException.class)
    public ResponseEntity<Map<String,String>> handleFeedbackListEmptyException(FeedbackListEmptyException ex){
        return ResponseEntity.status(404).body(Map.of("message",ex.getMessage()));
    }
}