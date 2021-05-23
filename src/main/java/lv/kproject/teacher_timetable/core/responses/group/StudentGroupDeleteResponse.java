package lv.kproject.teacher_timetable.core.responses.group;

import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.errors.CoreResponse;

import java.util.List;

public class StudentGroupDeleteResponse extends CoreResponse {

    private Long id;

    public StudentGroupDeleteResponse(List<CoreError> errors) {
        super(errors);
    }

    public StudentGroupDeleteResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
