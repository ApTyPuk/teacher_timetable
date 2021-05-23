package lv.kproject.teacher_timetable.core.services.group;

import lv.kproject.teacher_timetable.core.database.StudentGroupRepository;
import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.group.StudentGroupAddRequest;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.group.validators.StudentGroupAddValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGroupAddService {

    public static final int EDUCATION_DURATION = 1;
    private final StudentGroupRepository repository;
    private final StudentGroupAddValidator validator;

    public StudentGroupAddService(StudentGroupRepository repository, StudentGroupAddValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public StudentGroupAddUpdateResponse save(StudentGroupAddRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new StudentGroupAddUpdateResponse(errors);
        }

        StudentGroup group = new StudentGroup();
        group.setName(request.getName());
        group.setEducationYearStart(request.getEducationYearStart());
        group.setEducationYearEnd(request.getEducationYearStart() + EDUCATION_DURATION);
        repository.save(group);

        return new StudentGroupAddUpdateResponse(group);
    }
}
