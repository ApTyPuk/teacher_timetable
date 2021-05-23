package lv.kproject.teacher_timetable.core.services.teacher;

import lv.kproject.teacher_timetable.core.database.TeacherRepository;
import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.teacher.TeacherAddRequest;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.matchers.TeacherMatcher;
import lv.kproject.teacher_timetable.core.services.teacher.validators.TeacherAddValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;


@ExtendWith(MockitoExtension.class)
public class TeacherAddServiceTests {

    @Mock
    private TeacherRepository repository;
    @Mock
    private TeacherAddValidator validator;
    @InjectMocks
    private TeacherAddService service;

    @Test
    public void shouldReturnNoErrorsWhenValidatingWithFields() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        TeacherAddRequest request = new TeacherAddRequest();
        request.setName("TestName");
        request.setLastName("TestLastName");

        TeacherAddUpdateResponse response = service.save(request);
        assertFalse(response.hasErrors());
        Mockito.verify(repository).save(argThat(new TeacherMatcher("TestName", "TestLastName")));
    }

    @Test
    public void shouldReturnErrorsWhenValidatingFieldName() {
        TeacherAddRequest request = new TeacherAddRequest();
        request.setName(" ");
        request.setLastName("TestLastName");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        TeacherAddUpdateResponse response = service.save(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Name");

        Mockito.verifyNoInteractions(repository);
    }

    @Test
    public void shouldReturnErrorsWhenValidatingFieldPhone() {
        TeacherAddRequest request = new TeacherAddRequest();
        request.setName("TestName");
        request.setLastName("TestLastName");
        request.setPhoneNumber("A+37112345678");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Phone number",
                "can have only digits, plus '+' symbol with no spaces"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        TeacherAddUpdateResponse response = service.save(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Phone number");

        Mockito.verifyNoInteractions(repository);
    }



}

