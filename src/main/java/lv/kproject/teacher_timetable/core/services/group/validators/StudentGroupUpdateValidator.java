package lv.kproject.teacher_timetable.core.services.group.validators;

import lv.kproject.teacher_timetable.core.database.StudentGroupRepository;
import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.group.StudentGroupUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StudentGroupUpdateValidator {

    private final StudentGroupRepository repository;

    public StudentGroupUpdateValidator(StudentGroupRepository repository) {
        this.repository = repository;
    }

    public List<CoreError> validate(StudentGroupUpdateRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateIfGroupNamePresent(request).ifPresent(errors::add);
        validateGroupName(request).ifPresent(errors::add);
        validateIfStartYearHasWrongCharacters(request).ifPresent(errors::add);
        validateIfEndYearIsNotLessOrEqualToStartYear(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateIfGroupNamePresent(StudentGroupUpdateRequest request) {
        List<StudentGroup> nameList = repository.findByName(request.getName());
        if (!nameList.isEmpty() && request.getId().equals(nameList.get(0).getId())) {
            return Optional.empty();
        } else if (!nameList.isEmpty()){
            return Optional.of(new CoreError("Group name", "is already taken"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateGroupName(StudentGroupUpdateRequest request) {
        return (request.getName() == null || request.getName().isBlank()
                ? Optional.of(new CoreError("Name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateIfStartYearHasWrongCharacters(StudentGroupUpdateRequest request) {
        return (request.getEducationYearStart() == null || wrongCharactersPresent(request.getEducationYearStart())
                ? Optional.of(new CoreError("Start year",
                "can have only digits and should not be null"))
                : Optional.empty());
    }

    private Optional<CoreError> validateIfEndYearIsNotLessOrEqualToStartYear(StudentGroupUpdateRequest request) {
        return (request.getEducationYearStart() >= request.getEducationYearEnd()
                ? Optional.of(new CoreError("End year",
                "should be greater than start year"))
                : Optional.empty());
    }

    private boolean wrongCharactersPresent(Long description) {
        String regex = "[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(description));
        return matcher.find();
    }

}
