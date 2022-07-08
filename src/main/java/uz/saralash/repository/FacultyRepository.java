package uz.saralash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.saralash.entity.Faculty;
import uz.saralash.entity.University;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    List<Faculty> findAllByDeletedFalse();
    Optional<Faculty> findByName(String name);


}
