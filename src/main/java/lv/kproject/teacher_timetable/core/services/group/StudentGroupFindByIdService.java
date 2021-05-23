package lv.kproject.teacher_timetable.core.services.group;

import lv.kproject.teacher_timetable.core.database.StudentGroupRepository;
import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupFindByIdResponse;
import lv.kproject.teacher_timetable.core.services.group.validators.StudentGroupIdValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentGroupFindByIdService {

    private final StudentGroupRepository repository;
    private final StudentGroupIdValidator validator;

    public StudentGroupFindByIdService(StudentGroupRepository repository, StudentGroupIdValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public StudentGroupFindByIdResponse findGroupById(CheckIdRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new StudentGroupFindByIdResponse(errors);
        }

        Optional<StudentGroup> response = repository.findById(request.getId());

        if (response.isEmpty()) {
            errors.add(new CoreError("Student Group", "Not found"));
            return new StudentGroupFindByIdResponse(errors);
        }

        return new StudentGroupFindByIdResponse(response.get());
    }
}
