package lv.kproject.teacher_timetable.core.requests.lesson;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;

public class LessonUpdateRequest {

    @NotEmpty
    private Long id;
    @NotEmpty
    private String lessonName;
    @NotEmpty
    private Long teachersId;
//    private Teacher teachersId;
    @NotEmpty
    private Long studentGroupId;
    @NotEmpty
    private Long durationMinutes;
    @NotEmpty
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lessonDate;

    public LessonUpdateRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


//    public Teacher getTeachersId() {
//        return teachersId;
//    }
//
//    public void setTeachersId(Teacher teachersId) {
//        this.teachersId = teachersId;
//    }

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
        LessonUpdateRequest that = (LessonUpdateRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(lessonName, that.lessonName) &&
                Objects.equals(teachersId, that.teachersId) &&
                Objects.equals(studentGroupId, that.studentGroupId) &&
                Objects.equals(durationMinutes, that.durationMinutes) &&
                Objects.equals(lessonDate, that.lessonDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonName, teachersId, studentGroupId, durationMinutes, lessonDate);
    }

    @Override
    public String toString() {
        return "LessonUpdateRequest{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", teachersId=" + teachersId +
                ", studentGroupId=" + studentGroupId +
                ", durationMinutes=" + durationMinutes +
                ", lessonDate=" + lessonDate +
                '}';
    }
}
