package uz.saralash.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.saralash.entity.Group;
import uz.saralash.entity.University;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacultyDto {

    @NotNull(message = "Faculty name bo'lishi kerak")
    private String name;

    @NotNull(message = "Faculty qaysidir University ga tegishli bo'lishi kerak")
    private Integer universityId;

}
