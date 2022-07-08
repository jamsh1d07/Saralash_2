package uz.saralash.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;

    @Column(nullable = false)
    private Integer year;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<Student> students;

    private boolean deleted = false;

}
