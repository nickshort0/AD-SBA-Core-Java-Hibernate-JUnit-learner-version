package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    @Column(length = 50, nullable = false)
    String name;

    @NonNull
    @Column(length = 50, nullable = false)
    String instructor;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    List<Student> students = new ArrayList<>();

    public void addStudent(Student student){
        students.add(student);
        student.getCourses().add(this);
    }

}
