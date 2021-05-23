package lv.kproject.teacher_timetable.core.services.teacher.validators;

import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.teacher.TeacherUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TeacherUpdateValidator {

    public List<CoreError> validate(TeacherUpdateRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTeacherName(request).ifPresent(errors::add);
        validateTeacherLastName(request).ifPresent(errors::add);
        validateTeacherPhoneNumber(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateTeacherName(TeacherUpdateRequest request) {
        return (request.getName() == null || request.getName().isBlank()
                ? Optional.of(new CoreError("Name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateTeacherLastName(TeacherUpdateRequest request) {
        return (request.getLastName() == null || request.getLastName().isBlank()
                ? Optional.of(new CoreError("Last name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateTeacherPhoneNumber(TeacherUpdateRequest request) {
        return (wrongCharactersPresent(request.getPhoneNumber())
                ? Optional.of(new CoreError("Phone number",
                "can have only digits, plus '+' symbol with no spaces"))
                : Optional.empty());
    }

    private boolean wrongCharactersPresent(String description) {
        String regex = "[^[0-9]+]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(description);
        return matcher.find();
    }


}
