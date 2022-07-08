package uz.saralash.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class SearchByFacultyIdDto {


    private String name;

    private Integer count;
}
