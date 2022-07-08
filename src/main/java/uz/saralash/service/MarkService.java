package uz.saralash.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.saralash.entity.Mark;
import uz.saralash.entity.Student;
import uz.saralash.entity.Subject;
import uz.saralash.entity.Teacher;
import uz.saralash.payload.ApiResponse;
import uz.saralash.payload.MarkDto;
import uz.saralash.repository.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarkService {

    final MarkRepository markRepository;
    final TeacherRepository teacherRepository;
    final SubjectRepository subjectRepository;
    final StudentRepository studentRepository;

    public ApiResponse getAll() {
        return new ApiResponse("Get all Marks", true, markRepository.findAllByDeletedFalse());
    }

    public ApiResponse getOne(Integer id) {
        Optional<Mark> optionalMark = markRepository.findById(id);
        if (optionalMark.isPresent() && !optionalMark.get().isDeleted()) {
            return new ApiResponse("Get One Mark", true, optionalMark.get());
        }
        return new ApiResponse("No content", false);
    }

    public ApiResponse save(MarkDto dto) {
        Mark mark = new Mark();
        mark.setStudyYear(dto.getStudyYear());
        mark.setSemester(dto.getSemester());
        Optional<Student> optionalStudent = studentRepository.findById(dto.getStudentId());
        if (optionalStudent.isPresent() && !optionalStudent.get().isDeleted()) {

            mark.setStudent(optionalStudent.get());
            Optional<Subject> optionalSubject = subjectRepository.findById(dto.getSubjectId());
            if (optionalSubject.isPresent() && !optionalSubject.get().isDeleted()) {

                mark.setSubject(optionalSubject.get());
                Optional<Teacher> optionalTeacher = teacherRepository.findById(dto.getTeacherId());
                if (optionalTeacher.isPresent() && !optionalTeacher.get().isDeleted()) {

                    mark.setRating(dto.getRating());
                    mark.setTeacher(optionalTeacher.get());
                    markRepository.save(mark);

                    return new ApiResponse("Save Mark", true);
                }
                return new ApiResponse("The subject teacher was chosen incorrectly", false);
            }
            return new ApiResponse("Subject was selected incorrectly", false);
        }
        return new ApiResponse("There is no such student", false);
    }

    public ApiResponse edit(Integer id, MarkDto dto) {
        Optional<Mark> optionalMark = markRepository.findById(id);
        if (optionalMark.isPresent() && !optionalMark.get().isDeleted()) {
            Mark mark = optionalMark.get();
            Optional<Student> optionalStudent = studentRepository.findById(dto.getStudentId());
            if (optionalStudent.isPresent() && !optionalStudent.get().isDeleted()) {

                mark.setStudent(optionalStudent.get());
                Optional<Subject> optionalSubject = subjectRepository.findById(dto.getSubjectId());
                if (optionalSubject.isPresent() && !optionalSubject.get().isDeleted()) {

                    mark.setSubject(optionalSubject.get());
                    Optional<Teacher> optionalTeacher = teacherRepository.findById(dto.getTeacherId());
                    if (optionalTeacher.isPresent() && !optionalTeacher.get().isDeleted()) {

                        mark.setStudyYear(dto.getStudyYear());
                        mark.setSemester(dto.getSemester());
                        mark.setTeacher(optionalTeacher.get());
                        mark.setRating(dto.getRating());
                        markRepository.save(mark);
                        return new ApiResponse("Mark Edited", true);
                    }
                    return new ApiResponse("The subject teacher was chosen incorrectly", false);
                }
                return new ApiResponse("Subject was selected incorrectly", false);
            }
            return new ApiResponse("There is no such student", false);
        }
        return new ApiResponse("There is no such mark", false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Mark> optionalMark = markRepository.findById(id);
        if (optionalMark.isPresent()) {
            Mark mark = optionalMark.get();
            mark.setDeleted(true);
            markRepository.save(mark);
            return new ApiResponse("Deleted mark", true);
        }
        return new ApiResponse("No Deleted", false);
    }
}
