package uz.saralash.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.saralash.entity.Faculty;
import uz.saralash.entity.Group;
import uz.saralash.entity.Subject;
import uz.saralash.payload.ApiResponse;
import uz.saralash.payload.FacultyDto;
import uz.saralash.payload.GroupDto;
import uz.saralash.repository.FacultyRepository;
import uz.saralash.repository.GroupRepository;
import uz.saralash.repository.SubjectRepository;
import uz.saralash.repository.UniversityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    final GroupRepository groupRepository;

    final FacultyRepository facultyRepository;

    final SubjectRepository subjectRepository;

    public ApiResponse getAll() {
        return new ApiResponse("Get All", true, groupRepository.findAllByDeletedFalse());
    }

    public ApiResponse getOne(Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent() && !optionalGroup.get().isDeleted()) {
            return new ApiResponse("Get One Group", true, optionalGroup.get());
        }
        return new ApiResponse("No content", false);
    }


    public ApiResponse save(GroupDto dto) {
        Optional<Group> optionalGroup = groupRepository.findByName(dto.getName());
        if (optionalGroup.isEmpty()) {
            Group group = new Group();
            group.setName(dto.getName());
            Optional<Faculty> optionalFaculty = facultyRepository.findById(dto.getFacultyId());
            if (optionalFaculty.isPresent()) {
                group.setFaculty(optionalFaculty.get());
                group.setYear(dto.getYear());

                List<Integer> subjectIds = dto.getSubjectIds();
                List<Subject> subjects = new ArrayList<>();
                for (Integer subjectId : subjectIds) {
                    Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
                    if (optionalSubject.isPresent()){
                    Subject subject = optionalSubject.get();
                    subjects.add(subject);
                    }
                }
                group.setSubjects(subjects);
                groupRepository.save(group);
                return new ApiResponse("Save Group", true);
            }
            return new ApiResponse("There is a Faculty with the same name", false);
        }
        return new ApiResponse("There is such a group", false);
    }


    public ApiResponse edit(Integer id, GroupDto dto) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()&&!optionalGroup.get().isDeleted()) {
            Group group = optionalGroup.get();
            group.setName(dto.getName());
            group.setYear(dto.getYear());
            Optional<Faculty> optionalFaculty = facultyRepository.findById(dto.getFacultyId());
            if (optionalFaculty.isPresent()) {
                group.setFaculty(optionalFaculty.get());

                List<Integer> subjectIds = dto.getSubjectIds();
                List<Subject> subjects = new ArrayList<>();
                for (Integer subjectId : subjectIds) {
                    Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
                    if (optionalSubject.isPresent()){
                    Subject subject = optionalSubject.get();
                    subjects.add(subject);
                    }
                }
                group.setSubjects(subjects);
                groupRepository.save(group);
                return new ApiResponse("Edited Group", true);
            }
            return new ApiResponse("There is an error in Faculty",false);
        }
        return new ApiResponse("There is such a group", false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            group.setDeleted(true);
            groupRepository.save(group);
            return new ApiResponse("Deleted Group",true,group.getName());
        }
        return new ApiResponse("No Deleted",false);
    }
}
