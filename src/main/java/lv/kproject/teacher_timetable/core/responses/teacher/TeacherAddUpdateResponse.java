package lv.kproject.teacher_timetable.core.responses.teacher;

import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.errors.CoreResponse;

import java.util.List;

public class TeacherAddUpdateResponse extends CoreResponse {

    private Teacher teacher;

    public TeacherAddUpdateResponse(List<CoreError> errors) {
        super(errors);
    }

    public TeacherAddUpdateResponse(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

}
