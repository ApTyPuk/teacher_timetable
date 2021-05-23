package lv.kproject.teacher_timetable.core.responses.teacher;

import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.errors.CoreResponse;

import java.util.List;

public class TeacherDeleteResponse extends CoreResponse {

    private Long id;

    public TeacherDeleteResponse(List<CoreError> errors) {
        super(errors);
    }

    public TeacherDeleteResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
