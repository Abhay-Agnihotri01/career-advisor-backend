package com.careeradvisor.service;

import com.careeradvisor.dto.UserProfileDTO;
import com.careeradvisor.entity.UserProfile;
import com.careeradvisor.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public UserProfile createOrUpdateProfile(UserProfileDTO profileDTO) {
        Optional<UserProfile> existingProfile = userProfileRepository.findByEmail(profileDTO.getEmail());
        
        UserProfile profile;
        if (existingProfile.isPresent()) {
            profile = existingProfile.get();
        } else {
            profile = new UserProfile();
            profile.setEmail(profileDTO.getEmail());
        }
        
        profile.setFullName(profileDTO.getFullName());
        profile.setInterests(profileDTO.getInterests());
        profile.setSkills(profileDTO.getSkills());
        profile.setExperienceLevel(profileDTO.getExperienceLevel());
        profile.setEducation(profileDTO.getEducation());
        profile.setPreferredIndustries(profileDTO.getPreferredIndustries());
        
        return userProfileRepository.save(profile);
    }
    
    public Optional<UserProfile> getProfileById(Long id) {
        return userProfileRepository.findById(id);
    }
    
    public Optional<UserProfile> getProfileByEmail(String email) {
        return userProfileRepository.findByEmail(email);
    }
    
    public List<UserProfile> getAllProfiles() {
        return userProfileRepository.findAll();
    }
    
    public void deleteProfile(Long id) {
        userProfileRepository.deleteById(id);
    }
    
    public long getTotalUserCount() {
        return userProfileRepository.countTotalUsers();
    }
    
    public List<UserProfile> getProfilesByExperienceLevel(String level) {
        return userProfileRepository.findByExperienceLevel(level);
    }
    
    public UserProfileDTO convertToDTO(UserProfile profile) {
        return new UserProfileDTO(
            profile.getFullName(),
            profile.getEmail(),
            profile.getInterests(),
            profile.getSkills(),
            profile.getExperienceLevel(),
            profile.getEducation(),
            profile.getPreferredIndustries()
        );
    }
}