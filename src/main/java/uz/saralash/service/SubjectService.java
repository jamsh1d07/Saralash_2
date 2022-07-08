package uz.saralash.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.saralash.entity.Subject;
import uz.saralash.payload.ApiResponse;
import uz.saralash.payload.SubjectDto;
import uz.saralash.repository.SubjectRepository;
import uz.saralash.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    final SubjectRepository subjectRepository;

    public ApiResponse getAll() {
        return new ApiResponse("Get all Subjects",true,subjectRepository.findAllByDeletedFalse());
    }

    public ApiResponse getOne(Integer id) {
        Optional<Subject> byId = subjectRepository.findById(id);
        if (byId.isPresent()&&!byId.get().isDeleted()) {
            return new ApiResponse("Get One Subject",true,byId.get());
        }
        return new ApiResponse("No content",false);
    }

    public ApiResponse save(SubjectDto dto) {
        Optional<Subject> optionalSubject = subjectRepository.findByName(dto.getName());
        if (optionalSubject.isEmpty()) {
            Subject subject=new Subject();
            subject.setName(dto.getName());
            subjectRepository.save(subject);
            return new ApiResponse("Save Subject",true);
        }
        return new ApiResponse("There is a Subject with the same name", false);
    }

    public ApiResponse edit(Integer id, SubjectDto dto) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()&&!optionalSubject.get().isDeleted()) {
            Subject subject = optionalSubject.get();
            subject.setName(dto.getName());
            subjectRepository.save(subject);
            return new ApiResponse("Edited Subject",true);

        }
        return new ApiResponse("No Edited",false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            subject.setDeleted(true);
            subjectRepository.save(subject);
            return new ApiResponse("Deleted Subject",true,subject.getName());
        }
        return new ApiResponse("No Deleted",false);
    }

    public ApiResponse getSubjectByStudentId(Integer id) {
        List<String> list=subjectRepository.searchByStudentId(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Ok");
        apiResponse.setSuccess(true);
        apiResponse.setObject(list);
        return apiResponse;
    }
}
