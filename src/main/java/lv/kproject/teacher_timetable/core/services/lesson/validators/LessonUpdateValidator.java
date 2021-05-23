package lv.kproject.teacher_timetable.core.services.lesson.validators;

import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LessonUpdateValidator {

    public List<CoreError> validate(LessonUpdateRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateLessonName(request).ifPresent(errors::add);
        validateLessonDurationMinutes(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateLessonName(LessonUpdateRequest request) {
        return (request.getLessonName() == null || request.getLessonName().isBlank()
                ? Optional.of(new CoreError("Name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateLessonDurationMinutes(LessonUpdateRequest request) {
        return (wrongCharactersPresent(request.getDurationMinutes())
                ? Optional.of(new CoreError("Lesson duration",
                "can have only digits and should not be null"))
                : Optional.empty());
    }

    private boolean wrongCharactersPresent(Long description) {
        String regex = "[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(description));
        return matcher.find();
    }

}
