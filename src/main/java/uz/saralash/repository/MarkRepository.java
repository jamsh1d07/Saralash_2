package uz.saralash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.saralash.entity.Mark;
import uz.saralash.entity.University;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark,Integer> {
    List<Mark> findAllByDeletedFalse();

}
