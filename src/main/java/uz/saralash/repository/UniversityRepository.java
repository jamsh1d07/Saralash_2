package uz.saralash.repository;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.saralash.entity.University;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University,Integer> {
    Optional<University> findByName(String name);
    List<University> findAllByDeletedFalse();
}
