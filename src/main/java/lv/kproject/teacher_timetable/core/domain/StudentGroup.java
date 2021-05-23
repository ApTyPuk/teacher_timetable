package lv.kproject.teacher_timetable.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_groups")
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "education_year_start")
    private Long educationYearStart;
    @Column(name = "education_year_end")
    private Long educationYearEnd;

    public StudentGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEducationYearStart() {
        return educationYearStart;
    }

    public void setEducationYearStart(Long educationYearStart) {
        this.educationYearStart = educationYearStart;
    }

    public Long getEducationYearEnd() {
        return educationYearEnd;
    }

    public void setEducationYearEnd(Long educationYearEnd) {
        this.educationYearEnd = educationYearEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup studentGroup = (StudentGroup) o;
        return Objects.equals(id, studentGroup.id) &&
                Objects.equals(name, studentGroup.name) &&
                Objects.equals(educationYearStart, studentGroup.educationYearStart) &&
                Objects.equals(educationYearEnd, studentGroup.educationYearEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, educationYearStart, educationYearEnd);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", educationYearStart=" + educationYearStart +
                ", educationYearEnd=" + educationYearEnd +
                '}';
    }
}
