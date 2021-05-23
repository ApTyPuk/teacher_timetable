package lv.kproject.teacher_timetable.core.services.lesson;

import lv.kproject.teacher_timetable.core.database.LessonRepository;
import lv.kproject.teacher_timetable.core.database.StudentGroupRepository;
import lv.kproject.teacher_timetable.core.database.TeacherRepository;
import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonAddRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.lesson.validators.LessonAddValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LessonAddService {

    private final LessonRepository repository;
    private final StudentGroupRepository repositoryStudentGroup;
    private final TeacherRepository repositoryTeacher;
    private final LessonAddValidator validator;
//    private final TeacherFindByIdService serviceTeacher;
//    private final StudentGroupFindByIdService serviceStudentGroup;


    public LessonAddService(LessonRepository repository, StudentGroupRepository repositoryStudentGroup,
                            TeacherRepository repositoryTeacher, LessonAddValidator validator) {
        this.repository = repository;
        this.repositoryStudentGroup = repositoryStudentGroup;
        this.repositoryTeacher = repositoryTeacher;
        this.validator = validator;
    }

    public LessonAddUpdateResponse save(LessonAddRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LessonAddUpdateResponse(errors);
        }

        Lesson lesson = new Lesson();
        lesson.setLessonName(request.getLessonName());

        Optional<Teacher> teacher = repositoryTeacher.findById(request.getTeachersId());
        if (teacher.isEmpty()) {
            errors.add(new CoreError("Teacher", "Not found"));
            return new LessonAddUpdateResponse(errors);
        }
        lesson.setTeacher(teacher.get());

        Optional<StudentGroup> studentGroup = repositoryStudentGroup.findById(request.getStudentGroupId());
        if (studentGroup.isEmpty()) {
            errors.add(new CoreError("Student group", "Not found"));
            return new LessonAddUpdateResponse(errors);
        }
        lesson.setStudentGroup(studentGroup.get());

        lesson.setDurationMinutes(request.getDurationMinutes());
        lesson.setLessonDate(request.getLessonDate());
        repository.save(lesson);

        return new LessonAddUpdateResponse(lesson);
    }
}
