package lv.kproject.teacher_timetable.core.services.group;

import lv.kproject.teacher_timetable.core.database.StudentGroupRepository;
import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupShowAllResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupShowAllService {

    private final StudentGroupRepository repository;

    public StudentGroupShowAllService(StudentGroupRepository repository) {
        this.repository = repository;
    }

    public StudentGroupShowAllResponse showAllGroups() {
        List<StudentGroup> studentGroupList = repository.findAll();
        return new StudentGroupShowAllResponse(studentGroupList);
    }

}
