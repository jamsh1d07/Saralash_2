package uz.saralash.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.saralash.entity.Address;
import uz.saralash.entity.Subject;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDto {

    @NotBlank(message = "Teacher ni fullName i bo'lishi kerak")
    private String fullName;

    @NotBlank(message = "Teacher ni phoneNumber i bo'lishi kerak")
    private String phoneNumber;

    @NotNull(message = "Teacher ni Subject bo'lishi kerak")
    private List<Integer> subjectIds;

    @NotBlank(message = "Teacher ni aniq addresi bo'lishi kerak")
    private String city;

    @NotBlank(message = "Teacher ni aniq addressi bo'lishi kerak")
    private String home;

    @NotBlank(message = "Teacher ni aniq addressi bo'lishi kerak")
    private String street;

    private String gender;

    private String birtDate;

}
