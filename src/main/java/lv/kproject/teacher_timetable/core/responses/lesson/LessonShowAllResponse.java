package lv.kproject.teacher_timetable.core.responses.lesson;

import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.errors.CoreResponse;

import java.util.List;

public class LessonShowAllResponse extends CoreResponse {

    private List<Lesson> lessonList;

    public LessonShowAllResponse(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public LessonShowAllResponse(List<CoreError> errors, List<Lesson> lessonList) {
        super(errors);
        this.lessonList = lessonList;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }
}
