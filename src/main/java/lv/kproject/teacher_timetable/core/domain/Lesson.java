package lv.kproject.teacher_timetable.core.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "lesson_name")
    private String lessonName;
    @ManyToOne
    @JoinColumn(name = "teachers_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudentGroup studentGroup;
    @Column(name = "duration_minutes")
    private Long durationMinutes;
    @Column(name = "lesson_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lessonDate;

    public Lesson() {
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teachersId) {
        this.teacher = teachersId;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroupId) {
        this.studentGroup = studentGroupId;
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
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id) &&
                Objects.equals(lessonName, lesson.lessonName) &&
                Objects.equals(teacher, lesson.teacher) &&
                Objects.equals(studentGroup, lesson.studentGroup) &&
                Objects.equals(durationMinutes, lesson.durationMinutes) &&
                Objects.equals(lessonDate, lesson.lessonDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonName, teacher, studentGroup, durationMinutes, lessonDate);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", teachersId=" + teacher +
                ", groupId=" + studentGroup +
                ", durationMinutes=" + durationMinutes +
                ", lessonDate=" + lessonDate +
                '}';
    }
}
