package uz.saralash.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.saralash.entity.Student;
import uz.saralash.entity.Subject;
import uz.saralash.entity.Teacher;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarkDto {

    @NotNull(message = "Qaysi Studentga baho qo'yilishi aytilishi kerak")
    private Integer studentId;

    @NotNull(message = "Qaysi fandan baho qo'yilayotgani aytilishi kerak")
    private Integer subjectId;

    @NotBlank(message = "Qaysi o'quv yili uchun baho qo'yilayotgani aytilishi kerak")
    private String studyYear;

    @NotNull(message = "Qaysi semestr uchun baho qo'yilayotgani aytilishi kerak")
    private Integer semester;

    @NotNull(message = "Nechi ball qo'yayotgani aytilishi bo'lishi kerak")
    private Double rating;

    @NotNull(message = "Qaysi teacher tomonidan qo'yilayotgani aytilishi kerak")
    private Integer teacherId;

}
