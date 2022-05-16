package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Student {
    @Id
    @NonNull
    @Column(length = 50, unique = true, nullable = false, name = "email")
    String email;

    @NonNull
    @Column(length = 50, nullable = false, name = "name")
    String name;

    @NonNull
    @Column(length = 50, nullable = false, name = "password")
    String password;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_email"),
            inverseJoinColumns = @JoinColumn(name = "courses_id")
    )
    List<Course> courses = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return email.equals(student.email) && name.equals(student.name) && password.equals(student.password) && Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password, courses);
    }
}
