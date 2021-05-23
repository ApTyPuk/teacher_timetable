package lv.kproject.teacher_timetable.core.services.lesson;

import lv.kproject.teacher_timetable.core.database.LessonRepository;
import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonUpdateRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonDaoFindByIdResponse;
import lv.kproject.teacher_timetable.core.services.lesson.validators.LessonIdValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonObjectFindByIdService {

    private final LessonRepository repository;
    private final LessonIdValidator validator;

    public LessonObjectFindByIdService(LessonRepository repository, LessonIdValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public LessonDaoFindByIdResponse findLessonById(CheckIdRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LessonDaoFindByIdResponse(errors);
        }

        Optional<Lesson> responseLessonOptional = repository.findById(request.getId());
        if (responseLessonOptional.isEmpty()) {
            errors.add(new CoreError("Lesson", "Not found"));
            return new LessonDaoFindByIdResponse(errors);
        }

        Lesson lesson = responseLessonOptional.get();
        LessonUpdateRequest lessonUpdateObject = new LessonUpdateRequest();
        lessonUpdateObject.setId(lesson.getId());
        lessonUpdateObject.setLessonName(lesson.getLessonName());
        lessonUpdateObject.setTeachersId(lesson.getTeacher().getId());
        lessonUpdateObject.setStudentGroupId(lesson.getStudentGroup().getId());
        lessonUpdateObject.setDurationMinutes(lesson.getDurationMinutes());
        lessonUpdateObject.setLessonDate(lesson.getLessonDate());

        return new LessonDaoFindByIdResponse(lessonUpdateObject);
    }
}
