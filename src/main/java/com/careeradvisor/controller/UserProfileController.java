package com.careeradvisor.controller;

import com.careeradvisor.dto.UserProfileDTO;
import com.careeradvisor.entity.UserProfile;
import com.careeradvisor.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserProfileController {
    
    @Autowired
    private UserProfileService userProfileService;
    
    @PostMapping("/profile")
    public ResponseEntity<UserProfileDTO> createOrUpdateProfile(@Valid @RequestBody UserProfileDTO profileDTO) {
        try {
            UserProfile savedProfile = userProfileService.createOrUpdateProfile(profileDTO);
            UserProfileDTO responseDTO = userProfileService.convertToDTO(savedProfile);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileDTO> getProfileById(@PathVariable Long id) {
        Optional<UserProfile> profile = userProfileService.getProfileById(id);
        if (profile.isPresent()) {
            UserProfileDTO profileDTO = userProfileService.convertToDTO(profile.get());
            return new ResponseEntity<>(profileDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/profile/email/{email}")
    public ResponseEntity<UserProfileDTO> getProfileByEmail(@PathVariable String email) {
        Optional<UserProfile> profile = userProfileService.getProfileByEmail(email);
        if (profile.isPresent()) {
            UserProfileDTO profileDTO = userProfileService.convertToDTO(profile.get());
            return new ResponseEntity<>(profileDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/profiles")
    public ResponseEntity<List<UserProfileDTO>> getAllProfiles() {
        try {
            List<UserProfile> profiles = userProfileService.getAllProfiles();
            List<UserProfileDTO> profileDTOs = profiles.stream()
                .map(userProfileService::convertToDTO)
                .collect(Collectors.toList());
            return new ResponseEntity<>(profileDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/profile/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        try {
            userProfileService.deleteProfile(id);
            return new ResponseEntity<>("Profile deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting profile", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/stats/total")
    public ResponseEntity<Long> getTotalUserCount() {
        try {
            long count = userProfileService.getTotalUserCount();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/profiles/experience/{level}")
    public ResponseEntity<List<UserProfileDTO>> getProfilesByExperience(@PathVariable String level) {
        try {
            List<UserProfile> profiles = userProfileService.getProfilesByExperienceLevel(level);
            List<UserProfileDTO> profileDTOs = profiles.stream()
                .map(userProfileService::convertToDTO)
                .collect(Collectors.toList());
            return new ResponseEntity<>(profileDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}