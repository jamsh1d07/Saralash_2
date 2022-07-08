package uz.saralash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.saralash.entity.Group;
import uz.saralash.entity.University;
import uz.saralash.payload.SearchByFacultyIdDto;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Integer> {
    List<Group> findAllByDeletedFalse();
    Optional<Group> findByName(String name);


}
