package lv.kproject.teacher_timetable.core.services.teacher;

import lv.kproject.teacher_timetable.core.database.LessonRepository;
import lv.kproject.teacher_timetable.core.database.TeacherRepository;
import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonShowAllResponse;
import lv.kproject.teacher_timetable.core.services.teacher.validators.TeacherIdValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherFindLessons {

    private final LessonRepository repositoryLesson;
    private final TeacherRepository repositoryTeacher;
    private final TeacherIdValidator validator;

    public TeacherFindLessons(LessonRepository repositoryLesson, TeacherRepository repositoryTeacher,
                              TeacherIdValidator validator) {
        this.repositoryLesson = repositoryLesson;
        this.repositoryTeacher = repositoryTeacher;
        this.validator = validator;
    }

    public LessonShowAllResponse findTeachersLessons(CheckIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LessonShowAllResponse(errors, null);
        }

        Optional<Teacher> teacher = repositoryTeacher.findById(request.getId());
        if (teacher.isEmpty()) {
            errors.add(new CoreError("Teacher", "not found"));
            return new LessonShowAllResponse(errors, null);
        }

        List<Lesson> lessonsByTeacher = repositoryLesson.findLessonsByTeacherIs(teacher.get());
        return new LessonShowAllResponse(lessonsByTeacher);
    }
}
