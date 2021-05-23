package lv.kproject.teacher_timetable.core.responses.lesson;

import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.errors.CoreResponse;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonUpdateRequest;

import java.util.List;

public class LessonDaoFindByIdResponse extends CoreResponse {

    private LessonUpdateRequest lesson;

    public LessonDaoFindByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public LessonDaoFindByIdResponse(LessonUpdateRequest lesson) {
        this.lesson = lesson;
    }

    public LessonUpdateRequest getLesson() {
        return lesson;
    }
}
