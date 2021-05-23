package lv.kproject.teacher_timetable.core.services.lesson;

import lv.kproject.teacher_timetable.core.database.LessonRepository;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonDeleteResponse;
import lv.kproject.teacher_timetable.core.services.lesson.validators.LessonIdValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonDeleteService {

    private final LessonRepository repository;
    private final LessonIdValidator validator;

    public LessonDeleteService(LessonRepository repository, LessonIdValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public LessonDeleteResponse delete(CheckIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new LessonDeleteResponse(errors);
        }

        repository.deleteById(request.getId());
        return new LessonDeleteResponse(request.getId());
    }
}
