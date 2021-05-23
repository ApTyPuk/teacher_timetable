package lv.kproject.teacher_timetable.core.requests.group;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class StudentGroupAddRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private Long educationYearStart;

    public StudentGroupAddRequest() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroupAddRequest that = (StudentGroupAddRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(educationYearStart, that.educationYearStart) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, educationYearStart);
    }

    @Override
    public String toString() {
        return "StudentGroupAddRequest{" +
                "name='" + name + '\'' +
                ", educationYearStart=" + educationYearStart +
                '}';
    }
}
