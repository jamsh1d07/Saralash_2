package uz.saralash.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.saralash.entity.Address;
import uz.saralash.entity.Subject;
import uz.saralash.entity.Teacher;
import uz.saralash.payload.ApiResponse;
import uz.saralash.payload.TeacherDto;
import uz.saralash.repository.AddressRepository;
import uz.saralash.repository.SubjectRepository;
import uz.saralash.repository.TeacherRepository;
import uz.saralash.repository.UniversityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    final TeacherRepository teacherRepository;
    final AddressRepository addressRepository;
    final SubjectRepository subjectRepository;

    public ApiResponse getAll() {
        return new ApiResponse("Get all Teacher", true, teacherRepository.findAllByDeletedFalse());
    }

    public ApiResponse getOne(Integer id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent() && !optionalTeacher.get().isDeleted()) {
            return new ApiResponse("Get One Teacher", true, optionalTeacher.get());
        }
        return new ApiResponse("No Content", false);
    }

    public ApiResponse save(TeacherDto dto) {
        Teacher teacher = new Teacher();
        teacher.setFullName(dto.getFullName());
        teacher.setPhoneNumber(dto.getPhoneNumber());
        teacher.setBirtDate(dto.getBirtDate());
        teacher.setGender(dto.getGender());

        Address address = new Address();
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setHome(dto.getHome());
        addressRepository.save(address);

        teacher.setAddress(address);
        List<Integer> subjectIds = dto.getSubjectIds();
        List<Subject> subjects = new ArrayList<>();
        for (Integer subjectId : subjectIds) {
            Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
            if (optionalSubject.isPresent()) {
                subjects.add(optionalSubject.get());
            }
        }
        teacher.setSubjects(subjects);
        teacherRepository.save(teacher);
        return new ApiResponse("Save Teacher", true);
    }

    public ApiResponse edit(Integer id, TeacherDto dto) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent() && !teacherOptional.get().isDeleted()) {
            Teacher teacher = teacherOptional.get();
            teacher.setFullName(dto.getFullName());
            teacher.setPhoneNumber(dto.getPhoneNumber());
            teacher.setBirtDate(dto.getBirtDate());
            teacher.setGender(dto.getGender());
            Address address = teacher.getAddress();
            address.setCity(dto.getCity());
            address.setStreet(dto.getStreet());
            address.setHome(dto.getHome());
            teacher.setAddress(address);

            List<Integer> subjectIds = dto.getSubjectIds();
            List<Subject> subjects = new ArrayList<>();
            for (Integer subjectId : subjectIds) {
                Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
                if (optionalSubject.isPresent()) {
                    subjects.add(optionalSubject.get());
                }
            }
            teacher.setSubjects(subjects);
            teacherRepository.save(teacher);
            return new ApiResponse("Edited Teacher", true);
        }
        return new ApiResponse("There is no such teacher", false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setDeleted(true);
            teacherRepository.save(teacher);
            return new ApiResponse("Deleted Teacher", true);
        }
        return new ApiResponse("No Deleted", false);
    }
}
