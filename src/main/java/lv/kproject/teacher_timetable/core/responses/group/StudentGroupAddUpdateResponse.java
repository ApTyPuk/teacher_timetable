package lv.kproject.teacher_timetable.core.responses.group;

import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.errors.CoreResponse;

import java.util.List;

public class StudentGroupAddUpdateResponse extends CoreResponse {

    private StudentGroup studentGroup;

    public StudentGroupAddUpdateResponse(List<CoreError> errors) {
        super(errors);
    }

    public StudentGroupAddUpdateResponse(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

}
