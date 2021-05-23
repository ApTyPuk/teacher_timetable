/*
package lv.kproject.teacher_timetable.core.requests.lesson;

import lv.kproject.teacher_timetable.core.database.LessonRepository;
import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.lesson.validators.LessonAddValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonAddServiceOld {

    private final LessonRepository repository;
    private final LessonAddValidator validator;

    public LessonAddServiceOld(LessonRepository repository, LessonAddValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public LessonAddUpdateResponse save(LessonAddRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LessonAddUpdateResponse(errors);
        }

        Lesson lesson = new Lesson();
        lesson.setLessonName(request.getLessonName());
        lesson.setTeacher(request.getTeachersId());
        lesson.setStudentGroup(request.getStudentGroupId());
        lesson.setDurationMinutes(request.getDurationMinutes());
        lesson.setLessonDate(request.getLessonDate());
        repository.save(lesson);

        return new LessonAddUpdateResponse(lesson);
    }
}
*/
