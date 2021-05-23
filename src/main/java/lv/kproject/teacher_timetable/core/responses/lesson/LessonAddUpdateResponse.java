package lv.kproject.teacher_timetable.core.responses.lesson;

import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.errors.CoreResponse;

import java.util.List;

public class LessonAddUpdateResponse extends CoreResponse {

    private Lesson lesson;

    public LessonAddUpdateResponse(List<CoreError> errors) {
        super(errors);
    }

    public LessonAddUpdateResponse(Lesson lesson) {
        this.lesson = lesson;
    }

    public Lesson getLesson() {
        return lesson;
    }

}
