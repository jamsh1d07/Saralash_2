package uz.saralash.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.saralash.entity.Address;
import uz.saralash.entity.Group;
import uz.saralash.entity.Student;
import uz.saralash.payload.ApiResponse;
import uz.saralash.payload.SearchByFacultyIdDto;
import uz.saralash.payload.StudentDto;
import uz.saralash.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    final StudentRepository studentRepository;
    final AddressRepository addressRepository;
    final GroupRepository groupRepository;

    final SubjectRepository subjectRepository;

    public ApiResponse getAll() {
        return new ApiResponse("Get all Student",true,studentRepository.findAllByDeletedFalse());
    }

    public ApiResponse getOne(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()&&!optionalStudent.get().isDeleted()) {
            return new ApiResponse("One Student",true,optionalStudent.get());
        }
        return new ApiResponse("No content",false);
    }

    public ApiResponse save(StudentDto dto) {
        Student student=new Student();
        student.setFullName(dto.getFullName());
        student.setGender(dto.getGender());
        student.setBirtDate(dto.getBirtDate());
        Address address=new Address();
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setHome(dto.getHome());
        addressRepository.save(address);
        student.setAddress(address);
        student.setPhoneNumber(dto.getPhoneNumber());
        Optional<Group> optionalGroup = groupRepository.findById(dto.getGroupId());
        if (optionalGroup.isPresent()&&!optionalGroup.get().isDeleted()) {
            Group group = optionalGroup.get();
            student.setGroup(group);
            studentRepository.save(student);
            return new ApiResponse("Save Student",true);
        }
        return new ApiResponse("There is no such group",false);
    }

    public ApiResponse edit(Integer id, StudentDto dto) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()&&!optionalStudent.get().isDeleted()) {
            Student student = optionalStudent.get();
            student.setFullName(dto.getFullName());
            student.setPhoneNumber(dto.getPhoneNumber());
            student.setGender(dto.getGender());
            student.setBirtDate(dto.getBirtDate());
            Address address = student.getAddress();
            address.setCity(dto.getCity());
            address.setStreet(dto.getStreet());
            address.setHome(dto.getHome());
            student.setAddress(address);
            Optional<Group> groupOptional = groupRepository.findById(dto.getGroupId());
            if (groupOptional.isPresent()&&!groupOptional.get().isDeleted()) {
                Group group = groupOptional.get();
                student.setGroup(group);
                studentRepository.save(student);
                return new ApiResponse("Edited Student",true);
            }
            return new ApiResponse("There is no such group",false);
        }
        return new ApiResponse("There is no such student",false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setDeleted(true);
            studentRepository.save(student);
            return new ApiResponse("Deleted Student",true,student.getFullName());
        }
        return new ApiResponse("No Deleted",false);
    }

    public ApiResponse searchByFacultyId(Integer id) {
        List<SearchByFacultyIdDto> searchByFacultyIdDtos = studentRepository.searchByFacultyId(id);
        return new ApiResponse("Ok",true,searchByFacultyIdDtos);
    }
}
