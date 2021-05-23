package lv.kproject.teacher_timetable.core.services.group.validators;

import lv.kproject.teacher_timetable.core.database.StudentGroupRepository;
import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.group.StudentGroupAddRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StudentGroupAddValidator {

    private final StudentGroupRepository repository;

    public StudentGroupAddValidator(StudentGroupRepository repository) {
        this.repository = repository;
    }

    public List<CoreError> validate(StudentGroupAddRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateIfGroupNamePresent(request).ifPresent(errors::add);
        validateGroupName(request).ifPresent(errors::add);
        validateIfStartYearHasWrongCharacters(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateIfGroupNamePresent(StudentGroupAddRequest request) {
        List<StudentGroup> nameList = repository.findByName(request.getName());
        return (!nameList.isEmpty()
                ? Optional.of(new CoreError("Group name", "is already taken"))
                : Optional.empty());
    }

    private Optional<CoreError> validateGroupName(StudentGroupAddRequest request) {
        return (request.getName() == null || request.getName().isBlank()
                ? Optional.of(new CoreError("Name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateIfStartYearHasWrongCharacters(StudentGroupAddRequest request) {
        return (request.getEducationYearStart() == null || wrongCharactersPresent(request.getEducationYearStart())
                ? Optional.of(new CoreError("Start year",
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
