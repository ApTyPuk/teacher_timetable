package lv.kproject.teacher_timetable.core.services.teacher;

import lv.kproject.teacher_timetable.core.database.TeacherRepository;
import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherShowAllResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherShowAllService {

    private final TeacherRepository repository;

    public TeacherShowAllService(TeacherRepository repository) {
        this.repository = repository;
    }

    public TeacherShowAllResponse showAllTeachers() {
        List<Teacher> teacherList = repository.findAll();
        return new TeacherShowAllResponse(teacherList);
    }

}
