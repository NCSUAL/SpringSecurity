package project.security_study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.security_study.model.StudyData;

import java.util.Optional;

@Repository
public interface StudyRepository extends JpaRepository<StudyData, Integer> {
    Optional<StudyData> findByUsername(String username);
}
