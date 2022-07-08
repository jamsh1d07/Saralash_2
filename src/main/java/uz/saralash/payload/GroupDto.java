package uz.saralash.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.saralash.entity.Faculty;
import uz.saralash.entity.Subject;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {

    @NotBlank(message = "Groupni name bo'lishi kerak")
    private String name;

    @NotNull(message = "Group qaysidir Faculty ga tegishli bo'lishi kerak")
    private Integer facultyId;

    @NotNull(message = "Group ni ochilgan yili bo'lishi kerak")
    private Integer year;

    @NotNull(message = "Group da Subject bo'lishi kerak")
    private List<Integer> subjectIds;

}
