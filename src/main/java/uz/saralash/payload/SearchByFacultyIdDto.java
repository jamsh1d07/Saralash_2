package uz.saralash.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import uz.saralash.repository.StudentRepository;

import java.util.List;


public interface SearchByFacultyIdDto {


    @Value("#{target.group_name}")
    String getGroupName();

    @Value("#{target.counter}")
    Integer getCounter();

}
