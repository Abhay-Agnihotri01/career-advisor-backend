package com.careeradvisor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class UserProfileDTO {
    
    @NotBlank(message = "Full name is required")
    private String fullName;
    
    @Email(message = "Valid email is required")
    private String email;
    
    private List<String> interests;
    private List<String> skills;
    private String experienceLevel;
    private String education;
    private List<String> preferredIndustries;
    
    // Constructors
    public UserProfileDTO() {}
    
    public UserProfileDTO(String fullName, String email, List<String> interests, 
                         List<String> skills, String experienceLevel, String education,
                         List<String> preferredIndustries) {
        this.fullName = fullName;
        this.email = email;
        this.interests = interests;
        this.skills = skills;
        this.experienceLevel = experienceLevel;
        this.education = education;
        this.preferredIndustries = preferredIndustries;
    }
    
    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }
    
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    
    public String getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(String experienceLevel) { this.experienceLevel = experienceLevel; }
    
    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }
    
    public List<String> getPreferredIndustries() { return preferredIndustries; }
    public void setPreferredIndustries(List<String> preferredIndustries) { this.preferredIndustries = preferredIndustries; }
}