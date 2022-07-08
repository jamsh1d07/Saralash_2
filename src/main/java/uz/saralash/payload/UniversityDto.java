package uz.saralash.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniversityDto {

    @NotNull(message = "University ni name mi bo'lishi kerak")
    private String name;

    @NotNull(message = "University ni aniq addressi bo'lishi kerak")
    private String city;

    @NotNull(message = "University ni aniq addressi bo'lishi kerak")
    private String home;

    @NotNull(message = "University ni aniq addressi bo'lishi kerak")
    private String street;

    @NotNull(message = "University ni ochilgan yili bo'lishi kerak")
    private Integer openYear;

}
