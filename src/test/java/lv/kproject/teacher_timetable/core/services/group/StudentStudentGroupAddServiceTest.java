package lv.kproject.teacher_timetable.core.services.group;

import lv.kproject.teacher_timetable.core.database.StudentGroupRepository;
import lv.kproject.teacher_timetable.core.requests.group.StudentGroupAddRequest;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.matchers.GroupMatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
class StudentStudentGroupAddServiceTest {

    @Mock
    private StudentGroupRepository repository;
    @InjectMocks
    private StudentGroupAddService service;


    @Test
    void shouldReturnNoErrorsWhenValidatingWithFields() {
        StudentGroupAddRequest request = new StudentGroupAddRequest();
        request.setName("TestGroupName");

        StudentGroupAddUpdateResponse response = service.save(request);
        assertFalse(response.hasErrors());
        Mockito.verify(repository).save(argThat(new GroupMatcher("TestGroupName", LocalDate.now())));
    }

    @Test
    void shouldReturnErrorsWhenGroupIsNull() {
        StudentGroupAddRequest request = new StudentGroupAddRequest();

        StudentGroupAddUpdateResponse response = service.save(request);

        assertTrue(response.hasErrors());
        Mockito.verifyNoInteractions(repository);

    }




}