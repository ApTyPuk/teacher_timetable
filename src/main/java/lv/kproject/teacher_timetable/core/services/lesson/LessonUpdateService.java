package lv.kproject.teacher_timetable.core.services.lesson;

import lv.kproject.teacher_timetable.core.database.LessonRepository;
import lv.kproject.teacher_timetable.core.database.StudentGroupRepository;
import lv.kproject.teacher_timetable.core.database.TeacherRepository;
import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonUpdateRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.lesson.validators.LessonUpdateValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LessonUpdateService {

    private final LessonRepository repositoryLesson;
    private final StudentGroupRepository repositoryStudentGroup;
    private final TeacherRepository repositoryTeacher;
    private final LessonUpdateValidator validatorLesson;

    public LessonUpdateService(LessonRepository repositoryLesson, StudentGroupRepository repositoryStudentGroup, TeacherRepository repositoryTeacher, LessonUpdateValidator validatorLesson) {
        this.repositoryLesson = repositoryLesson;
        this.repositoryStudentGroup = repositoryStudentGroup;
        this.repositoryTeacher = repositoryTeacher;
        this.validatorLesson = validatorLesson;
    }

    public LessonAddUpdateResponse update(LessonUpdateRequest request){
        List<CoreError> errors = validatorLesson.validate(request);
        if (!errors.isEmpty()) {
            return new LessonAddUpdateResponse(errors);
        }

        Lesson lesson = new Lesson();
        lesson.setId(request.getId());
        lesson.setLessonName(request.getLessonName());

        Optional<Teacher> teacher = repositoryTeacher.findById(request.getTeachersId());
        if (teacher.isEmpty()) {
            errors.add(new CoreError("Teacher", "Not found"));
            return new LessonAddUpdateResponse(errors);
        }
        lesson.setTeacher(teacher.get());
//        lesson.setTeacher(request.getTeachersId());

        Optional<StudentGroup> studentGroup = repositoryStudentGroup.findById(request.getStudentGroupId());
        if (studentGroup.isEmpty()) {
            errors.add(new CoreError("Student group", "Not found"));
            return new LessonAddUpdateResponse(errors);
        }
        lesson.setStudentGroup(studentGroup.get());

        lesson.setDurationMinutes(request.getDurationMinutes());
        lesson.setLessonDate(request.getLessonDate());
        repositoryLesson.save(lesson);

        return new LessonAddUpdateResponse(lesson);
    }
}
