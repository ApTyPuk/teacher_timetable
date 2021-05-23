package lv.kproject.teacher_timetable.core.services.group;

import lv.kproject.teacher_timetable.core.database.StudentGroupRepository;
import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.group.StudentGroupUpdateRequest;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.group.validators.StudentGroupUpdateValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupUpdateService {

    private final StudentGroupRepository repository;
    private final StudentGroupUpdateValidator validator;

    public StudentGroupUpdateService(StudentGroupRepository repository, StudentGroupUpdateValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public StudentGroupAddUpdateResponse update(StudentGroupUpdateRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new StudentGroupAddUpdateResponse(errors);
        }

        StudentGroup group = new StudentGroup();
        group.setId(request.getId());
        group.setName(request.getName());
        group.setEducationYearStart(request.getEducationYearStart());
        group.setEducationYearEnd(request.getEducationYearEnd());

        repository.save(group);

        return new StudentGroupAddUpdateResponse(group);
    }
}
