package lv.kproject.teacher_timetable.core.services.teacher.validators;//package lv.kproject.teacher_timetable.core.services.teacher.validators;


import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.requests.teacher.TeacherAddRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TeacherAddValidatorTest {

    private TeacherAddValidator validator;
    private TeacherAddRequest request;

    @BeforeEach
    public void beforeEach() {
        validator = new TeacherAddValidator();
        request = new TeacherAddRequest();
    }

    @Test
    public void shouldValidateWithNoErrors() {
        request.setName("TestName");
        request.setLastName("TestLastName");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldValidateWithErrorNameIsEmpty() {
        request.setName("");
        request.setLastName("TestLastName");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Name", errors.get(0).getField());
    }

    @Test
    public void shouldValidateWithErrorLastNameIsEmpty() {
        request.setName("TestName");
        request.setLastName(" ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Last name", errors.get(0).getField());
    }

    @Test
    public void shouldValidateWithErrorInvalidPhoneNumber() {
        request.setName("TestName");
        request.setLastName("TestLastName");
        request.setPhoneNumber("A+37112345678");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Phone number", errors.get(0).getField());
    }



}