package lv.kproject.teacher_timetable.core.services.teacher;

import lv.kproject.teacher_timetable.core.database.TeacherRepository;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherDeleteResponse;
import lv.kproject.teacher_timetable.core.services.teacher.validators.TeacherIdValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherDeleteService {

    private final TeacherRepository repository;
    private final TeacherIdValidator validator;

    public TeacherDeleteService(TeacherRepository repository, TeacherIdValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public TeacherDeleteResponse delete(CheckIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new TeacherDeleteResponse(errors);
        }

        repository.deleteById(request.getId());
        return new TeacherDeleteResponse(request.getId());
    }
}
