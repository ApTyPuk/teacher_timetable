package lv.kproject.teacher_timetable.core.responses.group;

import lv.kproject.teacher_timetable.core.domain.StudentGroup;

import java.util.List;

public class StudentGroupShowAllResponse {

    private List<StudentGroup> studentGroupList;

    public StudentGroupShowAllResponse(List<StudentGroup> studentGroupList) {
        this.studentGroupList = studentGroupList;
    }

    public List<StudentGroup> getStudentGroupList() {
        return studentGroupList;
    }
}
