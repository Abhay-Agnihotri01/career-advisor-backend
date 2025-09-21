package com.careeradvisor.service;

import com.careeradvisor.entity.PersonalityAssessment;
import com.careeradvisor.repository.PersonalityAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class PersonalityAssessmentService {
    
    @Autowired
    private PersonalityAssessmentRepository repository;
    
    public PersonalityAssessment calculatePersonality(Long userId, Map<Integer, String> responses) {
        PersonalityAssessment assessment = new PersonalityAssessment();
        assessment.setUserId(userId);
        assessment.setResponses(responses);
        
        // Calculate scores based on responses
        int red = 0, yellow = 0, green = 0, blue = 0;
        
        for (Map.Entry<Integer, String> entry : responses.entrySet()) {
            String answer = entry.getValue().toLowerCase();
            switch (answer) {
                case "a": red++; break;
                case "b": yellow++; break;
                case "c": green++; break;
                case "d": blue++; break;
            }
        }
        
        assessment.setRedScore(red);
        assessment.setYellowScore(yellow);
        assessment.setGreenScore(green);
        assessment.setBlueScore(blue);
        
        // Determine dominant color
        String dominant = "RED";
        int maxScore = red;
        if (yellow > maxScore) { dominant = "YELLOW"; maxScore = yellow; }
        if (green > maxScore) { dominant = "GREEN"; maxScore = green; }
        if (blue > maxScore) { dominant = "BLUE"; }
        
        assessment.setDominantColor(dominant);
        
        return repository.save(assessment);
    }
    
    public PersonalityAssessment getByUserId(Long userId) {
        return repository.findByUserId(userId).orElse(null);
    }
}