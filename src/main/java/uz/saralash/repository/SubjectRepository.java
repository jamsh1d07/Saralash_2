package uz.saralash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.saralash.entity.Student;
import uz.saralash.entity.Subject;
import uz.saralash.entity.University;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    List<Subject> findAllByDeletedFalse();
    Optional<Subject> findByName(String name);
    @Query(nativeQuery = true,value = "select s.name\n" +
            "from subject s\n" +
            "         join groups_subjects gs on s.id = gs.subjects_id\n" +
            "where gs.groups_id in (select g2.id\n" +
            "                       from groups g2\n" +
            "                                join student s2 on g2.id = s2.group_id\n" +
            "                       where s2.id = :id)")
    List<String> searchByStudentId(@Param("id") Integer id);
}
