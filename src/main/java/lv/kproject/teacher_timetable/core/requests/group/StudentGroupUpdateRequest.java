package lv.kproject.teacher_timetable.core.requests.group;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class StudentGroupUpdateRequest {

    @NotEmpty
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private Long educationYearStart;
    @NotEmpty
    private Long educationYearEnd;

    public StudentGroupUpdateRequest() {
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
        StudentGroupUpdateRequest that = (StudentGroupUpdateRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(educationYearStart, that.educationYearStart) &&
                Objects.equals(educationYearEnd, that.educationYearEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, educationYearStart, educationYearEnd);
    }

    @Override
    public String toString() {
        return "StudentGroupUpdateRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", educationYearStart=" + educationYearStart +
                ", educationYearEnd=" + educationYearEnd +
                '}';
    }
}
