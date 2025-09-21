package com.careeradvisor.repository;

import com.careeradvisor.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    
    Optional<UserProfile> findByEmail(String email);
    
    @Query("SELECT u FROM UserProfile u WHERE u.experienceLevel = :level")
    List<UserProfile> findByExperienceLevel(@Param("level") String experienceLevel);
    
    @Query("SELECT u FROM UserProfile u WHERE u.education = :education")
    List<UserProfile> findByEducation(@Param("education") String education);
    
    @Query("SELECT COUNT(u) FROM UserProfile u")
    long countTotalUsers();
}