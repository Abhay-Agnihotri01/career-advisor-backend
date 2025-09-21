package com.careeradvisor.repository;

import com.careeradvisor.entity.PersonalityAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonalityAssessmentRepository extends JpaRepository<PersonalityAssessment, Long> {
    Optional<PersonalityAssessment> findByUserId(Long userId);
}