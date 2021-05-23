package lv.kproject.teacher_timetable.core.services.teacher;

import lv.kproject.teacher_timetable.core.database.LessonRepository;
import lv.kproject.teacher_timetable.core.database.TeacherRepository;
import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonIntervalRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonShowAllResponse;
import lv.kproject.teacher_timetable.core.services.teacher.validators.TeacherIdValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherFindLessonsInIntervalService {

    private final LessonRepository repositoryLesson;
    private final TeacherRepository repositoryTeacher;
    private final TeacherIdValidator validator;

    public TeacherFindLessonsInIntervalService(LessonRepository repositoryLesson,
                                               TeacherRepository repositoryTeacher,
                                               TeacherIdValidator validator) {
        this.repositoryLesson = repositoryLesson;
        this.repositoryTeacher = repositoryTeacher;
        this.validator = validator;
    }

    public LessonShowAllResponse findTeachersLessonsInInterval(LessonIntervalRequest requestIntervalDate,
                                                               CheckIdRequest requestId) {
        List<CoreError> errors = validator.validate(requestId);
        if (!errors.isEmpty()) {
            return new LessonShowAllResponse(errors, null);
        }

        Optional<Teacher> teacher = repositoryTeacher.findById(requestId.getId());
        if (teacher.isEmpty()) {
            errors.add(new CoreError("Teacher", "not found"));
            return new LessonShowAllResponse(errors, null);
        }

        List<Lesson> lessonsByTeacher =
                repositoryLesson.findAllByLessonDateBetweenAndTeacherIs(
                        requestIntervalDate.getLessonStart(), requestIntervalDate.getLessonEnd(), teacher.get());
        return new LessonShowAllResponse(lessonsByTeacher);
    }
}
