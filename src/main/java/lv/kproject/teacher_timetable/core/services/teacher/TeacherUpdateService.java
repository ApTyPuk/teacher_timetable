package lv.kproject.teacher_timetable.core.services.teacher;

import lv.kproject.teacher_timetable.core.database.TeacherRepository;
import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.teacher.TeacherUpdateRequest;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.teacher.validators.TeacherUpdateValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherUpdateService {

    private final TeacherRepository repository;
    private final TeacherUpdateValidator validator;

    public TeacherUpdateService(TeacherRepository repository, TeacherUpdateValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public TeacherAddUpdateResponse update(TeacherUpdateRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new TeacherAddUpdateResponse(errors);
        }

        Teacher teacher = new Teacher();
        teacher.setId(request.getId());
        teacher.setName(request.getName());
        teacher.setLastName(request.getLastName());
        teacher.setPhoneNumber(request.getPhoneNumber());
        teacher.setEmail(request.getEmail());
        teacher.setHourRate(request.getHourRate());

        repository.save(teacher);

        return new TeacherAddUpdateResponse(teacher);
    }
}
