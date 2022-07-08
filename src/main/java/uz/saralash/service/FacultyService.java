package uz.saralash.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.saralash.entity.Faculty;
import uz.saralash.entity.University;
import uz.saralash.payload.ApiResponse;
import uz.saralash.payload.FacultyDto;
import uz.saralash.payload.UniversityDto;
import uz.saralash.repository.FacultyRepository;
import uz.saralash.repository.UniversityRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyService {

    final FacultyRepository facultyRepository;
    final UniversityRepository universityRepository;

    public ApiResponse getAll() {
        return new ApiResponse("Get All", true, facultyRepository.findAllByDeletedFalse());
    }

    public ApiResponse getOne(Integer id) {
        Optional<Faculty> byId = facultyRepository.findById(id);
        if (byId.isPresent() && !byId.get().isDeleted()) {
            return new ApiResponse("Get One Faculty", true, byId.get());
        }
        return new ApiResponse("No content", false);
    }

    public ApiResponse save(FacultyDto dto) {
        Faculty faculty = new Faculty();
        Optional<Faculty> optionalFaculty = facultyRepository.findByName(dto.getName());
        if (optionalFaculty.isEmpty()) {
            faculty.setName(dto.getName());
            Optional<University> byId = universityRepository.findById(dto.getUniversityId());
            if (byId.isPresent() && !byId.get().isDeleted()) {
                faculty.setUniversity(byId.get());
                facultyRepository.save(faculty);
                return new ApiResponse("Save Faculty", true);
            }
            return new ApiResponse("No University with such id found.", false);
        }
        return new ApiResponse("There is a Faculty with the same name", false);

    }


    public ApiResponse edit(Integer id, FacultyDto dto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent() && !optionalFaculty.get().isDeleted()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(dto.getName());
            Optional<University> optionalUniversity = universityRepository.findById(dto.getUniversityId());
            if (optionalUniversity.isPresent() && !optionalUniversity.get().isDeleted()) {
                faculty.setUniversity(optionalUniversity.get());
                facultyRepository.save(faculty);
                return new ApiResponse("Edited Faculty", true);
            }
            return new ApiResponse("No University with such id found.", false);
        }
        return new ApiResponse("No Faculty with such id found.",false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setDeleted(true);
            facultyRepository.save(faculty);
            return new ApiResponse("Deleted Faculty",true,faculty.getName());
        }
        return new ApiResponse("No Deleted",false);
    }

}
