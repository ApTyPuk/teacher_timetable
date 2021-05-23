package lv.kproject.teacher_timetable.core.requests.lesson;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;

public class LessonAddRequest {

    @NotEmpty
    private String lessonName;
    @NotEmpty
    private Long teachersId;
    @NotEmpty
    private Long studentGroupId;
    @NotEmpty
    private Long durationMinutes;
    @NotEmpty
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lessonDate;

    public LessonAddRequest() {
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Long getTeachersId() {
        return teachersId;
    }

    public void setTeachersId(Long teachersId) {
        this.teachersId = teachersId;
    }

    public Long getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(Long studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public Long getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Long durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDateTime getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(LocalDateTime lessonDate) {
        this.lessonDate = lessonDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonAddRequest that = (LessonAddRequest) o;
        return Objects.equals(lessonName, that.lessonName) &&
                Objects.equals(teachersId, that.teachersId) &&
                Objects.equals(studentGroupId, that.studentGroupId) &&
                Objects.equals(durationMinutes, that.durationMinutes) &&
                Objects.equals(lessonDate, that.lessonDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonName, teachersId, studentGroupId, durationMinutes, lessonDate);
    }

    @Override
    public String toString() {
        return "LessonAddRequest{" +
                "lessonName='" + lessonName + '\'' +
                ", teachersId=" + teachersId +
                ", studentGroupId=" + studentGroupId +
                ", durationMinutes=" + durationMinutes +
                ", lessonDate=" + lessonDate +
                '}';
    }
}
