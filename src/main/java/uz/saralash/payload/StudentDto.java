package uz.saralash.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.saralash.entity.Address;
import uz.saralash.entity.Group;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {

    @NotBlank(message = "Studentning fullName i bo'lishi kerak")
    private String fullName;

    @NotBlank(message = "Studentning phoneNumber i bo'lishi kerak")
    private String phoneNumber;

    @NotNull(message = "Studentning group i bo'lishi kerak")
    private Integer groupId;

    @NotBlank(message = "Student ni aniq addresi bo'lishi kerak")
    private String city;

    @NotBlank(message = "Student ni aniq addressi bo'lishi kerak")
    private String home;

    @NotBlank(message = "Student ni aniq addressi bo'lishi kerak")
    private String street;

    private String gender;

    private String birtDate;

}
