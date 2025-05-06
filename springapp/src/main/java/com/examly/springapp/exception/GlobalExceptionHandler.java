package com.examly.springapp.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Map;
@ControllerAdvice
public class GlobalExceptionHandler {
 
   @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new ConcurrentHashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
}
  
   @ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<ConcurrentMap<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
    ConcurrentMap<String, String> errorResponse = new ConcurrentHashMap<>();
    errorResponse.put("message", ex.getMessage());
    return ResponseEntity.status(404).body(errorResponse);
}
@ExceptionHandler(VehicleMaintenanceServiceNotFoundException.class)
public ResponseEntity<ConcurrentMap<String, String>> handleVehicleMaintenanceServiceNotFoundException(VehicleMaintenanceServiceNotFoundException ex) {
    ConcurrentMap<String, String> errorResponse = new ConcurrentHashMap<>();
    errorResponse.put("message", ex.getMessage());
    return ResponseEntity.status(404).body(errorResponse);
}

@ExceptionHandler(FeedbackNotFoundException.class)
public ResponseEntity<ConcurrentMap<String, String>> handleFeedbackNotFoundException(FeedbackNotFoundException ex) {
    ConcurrentMap<String, String> errorResponse = new ConcurrentHashMap<>();
    errorResponse.put("message", ex.getMessage());
    return ResponseEntity.status(404).body(errorResponse);
}

@ExceptionHandler(AppointmentNotFoundException.class)
public ResponseEntity<ConcurrentMap<String, String>> handleAppointmentNotFoundException(AppointmentNotFoundException ex) {
    ConcurrentMap<String, String> errorResponse = new ConcurrentHashMap<>();
    errorResponse.put("message", ex.getMessage());
    return ResponseEntity.status(404).body(errorResponse);
}

@ExceptionHandler(VehicleMaintenanceListEmptyException.class)
public ResponseEntity<ConcurrentMap<String, String>> handleVehicleMaintenanceListEmptyException(VehicleMaintenanceListEmptyException ex) {
    ConcurrentMap<String, String> errorResponse = new ConcurrentHashMap<>();
    errorResponse.put("message", ex.getMessage());
    return ResponseEntity.status(404).body(errorResponse);
}

@ExceptionHandler(FeedbackListEmptyException.class)
public ResponseEntity<ConcurrentMap<String, String>> handleFeedbackListEmptyException(FeedbackListEmptyException ex) {
    ConcurrentMap<String, String> errorResponse = new ConcurrentHashMap<>();
    errorResponse.put("message", ex.getMessage());
    return ResponseEntity.status(404).body(errorResponse);
}
}