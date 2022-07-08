package uz.saralash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.saralash.entity.Student;
import uz.saralash.entity.University;
import uz.saralash.payload.SearchByFacultyIdDto;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findAllByDeletedFalse();

    @Query(value = "select g.name as group_name, count(s.id) as counter\n" +
            "        from student s\n" +
            "                 join groups g on s.group_id = g.id\n" +
            "        where g.id in (select g.id\n" +
            "                       from groups g\n" +
            "                                join faculty f on g.faculty_id = f.id\n" +
            "                       where f.id = :id)\n" +
            "        group by group_name", nativeQuery = true)
    List<SearchByFacultyIdDto> searchByFacultyId(@Param("id") Integer id);
}
