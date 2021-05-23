package lv.kproject.teacher_timetable.core.services.teacher.validators;

import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TeacherIdValidator {

    public List<CoreError> validate(CheckIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTeacherId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateTeacherId(CheckIdRequest request) {
        return (request.getId() == null
                ? Optional.of(new CoreError("Id", "must not be empty"))
                : Optional.empty());
    }


}
