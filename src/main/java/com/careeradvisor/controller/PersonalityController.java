package com.careeradvisor.controller;

import com.careeradvisor.entity.PersonalityAssessment;
import com.careeradvisor.service.PersonalityAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/personality")
@CrossOrigin(origins = "http://localhost:5173")
public class PersonalityController {
    
    @Autowired
    private PersonalityAssessmentService service;
    
    @PostMapping("/assess")
    public ResponseEntity<PersonalityAssessment> submitAssessment(
            @RequestParam Long userId,
            @RequestBody Map<Integer, String> responses) {
        PersonalityAssessment result = service.calculatePersonality(userId, responses);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/result/{userId}")
    public ResponseEntity<PersonalityAssessment> getResult(@PathVariable Long userId) {
        PersonalityAssessment result = service.getByUserId(userId);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
}