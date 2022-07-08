package uz.saralash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.saralash.payload.ApiResponse;
import uz.saralash.payload.GroupDto;
import uz.saralash.payload.SubjectDto;
import uz.saralash.repository.SubjectRepository;
import uz.saralash.repository.UniversityRepository;
import uz.saralash.service.SubjectService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor
public class SubjectController {
    final SubjectService subjectService;

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse response = subjectService.getAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        ApiResponse response = subjectService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }
    @GetMapping( "/saerchByStudentId/{id}")
    public HttpEntity<?>getSubjects(@PathVariable Integer id){
        ApiResponse subjectByStudentId = subjectService.getSubjectByStudentId(id);
        return ResponseEntity.ok(subjectByStudentId);
    }
    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody SubjectDto dto){
        ApiResponse response = subjectService.save(dto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody SubjectDto dto){
        ApiResponse response = subjectService.edit(id, dto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = subjectService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    //--------------------------VALIDATION-----------------------
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
