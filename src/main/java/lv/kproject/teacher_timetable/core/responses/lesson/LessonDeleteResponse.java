package lv.kproject.teacher_timetable.core.responses.lesson;

import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.errors.CoreResponse;

import java.util.List;

public class LessonDeleteResponse extends CoreResponse {

    private Long id;

    public LessonDeleteResponse(List<CoreError> errors) {
        super(errors);
    }

    public LessonDeleteResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
