package lv.kproject.teacher_timetable.core.responses.teacher;

import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.errors.CoreResponse;

import java.util.List;

public class TeacherFindByIdResponse extends CoreResponse {

    private Teacher teacher;

    public TeacherFindByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public TeacherFindByIdResponse(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
