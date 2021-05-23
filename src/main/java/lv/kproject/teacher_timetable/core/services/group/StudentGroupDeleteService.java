package lv.kproject.teacher_timetable.core.services.group;

import lv.kproject.teacher_timetable.core.database.StudentGroupRepository;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupDeleteResponse;
import lv.kproject.teacher_timetable.core.services.group.validators.StudentGroupIdValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupDeleteService {

    private final StudentGroupRepository repository;
    private final StudentGroupIdValidator validator;

    public StudentGroupDeleteService(StudentGroupRepository repository, StudentGroupIdValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public StudentGroupDeleteResponse delete(CheckIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new StudentGroupDeleteResponse(errors);
        }

        repository.deleteById(request.getId());
        return new StudentGroupDeleteResponse(request.getId());

    }
}
