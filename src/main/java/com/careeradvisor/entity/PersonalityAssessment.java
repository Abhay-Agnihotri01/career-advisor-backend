package com.careeradvisor.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "personality_assessments")
public class PersonalityAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    @ElementCollection
    @CollectionTable(name = "assessment_responses")
    @MapKeyColumn(name = "question_id")
    @Column(name = "response")
    private Map<Integer, String> responses;
    
    @Column(name = "red_score")
    private Integer redScore = 0;
    
    @Column(name = "yellow_score")
    private Integer yellowScore = 0;
    
    @Column(name = "green_score")
    private Integer greenScore = 0;
    
    @Column(name = "blue_score")
    private Integer blueScore = 0;
    
    @Column(name = "dominant_color")
    private String dominantColor;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public PersonalityAssessment() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Map<Integer, String> getResponses() { return responses; }
    public void setResponses(Map<Integer, String> responses) { this.responses = responses; }
    
    public Integer getRedScore() { return redScore; }
    public void setRedScore(Integer redScore) { this.redScore = redScore; }
    
    public Integer getYellowScore() { return yellowScore; }
    public void setYellowScore(Integer yellowScore) { this.yellowScore = yellowScore; }
    
    public Integer getGreenScore() { return greenScore; }
    public void setGreenScore(Integer greenScore) { this.greenScore = greenScore; }
    
    public Integer getBlueScore() { return blueScore; }
    public void setBlueScore(Integer blueScore) { this.blueScore = blueScore; }
    
    public String getDominantColor() { return dominantColor; }
    public void setDominantColor(String dominantColor) { this.dominantColor = dominantColor; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}