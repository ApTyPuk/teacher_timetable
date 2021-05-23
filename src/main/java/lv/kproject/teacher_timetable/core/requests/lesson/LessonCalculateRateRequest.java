package lv.kproject.teacher_timetable.core.requests.lesson;

import lombok.*;
import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.domain.Teacher;

import java.util.List;

@NoArgsConstructor @EqualsAndHashCode @ToString
public class LessonCalculateRateRequest {

    private List<Lesson> lessonList;
    private Teacher teacher;

    public LessonCalculateRateRequest(List<Lesson> lessonList, Teacher teacher) {
        this.lessonList = lessonList;
        this.teacher = teacher;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
