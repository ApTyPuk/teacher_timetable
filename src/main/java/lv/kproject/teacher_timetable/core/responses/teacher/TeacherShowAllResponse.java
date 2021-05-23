package lv.kproject.teacher_timetable.core.responses.teacher;

import lv.kproject.teacher_timetable.core.domain.Teacher;

import java.util.List;

public class TeacherShowAllResponse {

    private List<Teacher> teacherList;

    public TeacherShowAllResponse(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

}
