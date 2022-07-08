package uz.saralash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.saralash.entity.Teacher;
import uz.saralash.entity.University;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    List<Teacher> findAllByDeletedFalse();
}
