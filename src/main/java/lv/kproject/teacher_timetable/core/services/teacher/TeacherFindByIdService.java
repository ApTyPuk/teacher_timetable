package lv.kproject.teacher_timetable.core.services.teacher;

import lv.kproject.teacher_timetable.core.database.TeacherRepository;
import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherFindByIdResponse;
import lv.kproject.teacher_timetable.core.services.teacher.validators.TeacherIdValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherFindByIdService {

    private final TeacherRepository repository;
    private final TeacherIdValidator validator;

    public TeacherFindByIdService(TeacherRepository repository, TeacherIdValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public TeacherFindByIdResponse findTeacherById(CheckIdRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new TeacherFindByIdResponse(errors);
        }

        Optional<Teacher> response = repository.findById(request.getId());

        if (response.isEmpty()) {
            errors.add(new CoreError("Teacher", "Not found"));
            return new TeacherFindByIdResponse(errors);
        }

        return new TeacherFindByIdResponse(response.get());
    }
}
