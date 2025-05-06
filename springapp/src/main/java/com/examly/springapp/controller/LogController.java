package com.examly.springapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @PostMapping("/api/logs")
    public ResponseEntity<Void> receiveLogs(@RequestBody Map<String, String> logMessage) {
        logger.info("Received log: {}", logMessage.get("message"));
        return ResponseEntity.ok().build();
    }

}
