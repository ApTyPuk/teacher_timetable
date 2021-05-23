package lv.kproject.teacher_timetable.core.services.lesson;

import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonCalculateRateRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonCalculateRateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class LessonCalculateRateServiceTest {

    @InjectMocks
    private LessonCalculateRateService service;
    private List<Lesson> lessonList;

    @BeforeEach
    public void beforeEach() {
        populateLessonList();
    }

    @Test
    void shouldReturnNoErrorsWhenCheckingWage() {
        LessonCalculateRateRequest request = new LessonCalculateRateRequest(lessonList, createTeacher());
        LessonCalculateRateResponse response = service.calculateTeachersEarning(request);
        assertEquals(new BigDecimal("30"), response.getMoneyEarned());
    }


    @Test
    void shouldReturnNoErrorsWhenCheckingTotalMinutes() {
        LessonCalculateRateRequest request = new LessonCalculateRateRequest(lessonList, createTeacher());
        LessonCalculateRateResponse response = service.calculateTeachersEarning(request);
        assertEquals(180, response.getTotalNumberOfMinutesWorked());
    }

    @Test
    void shouldReturnNoErrorsWhenCheckingTotalHours() {
        LessonCalculateRateRequest request = new LessonCalculateRateRequest(lessonList, createTeacher());
        LessonCalculateRateResponse response = service.calculateTeachersEarning(request);
        assertEquals(3, response.getHourEquivalent());
    }

    @Test
    void shouldReturnNoErrorsWhenCheckingHourMinutes() {
        LessonCalculateRateRequest request = new LessonCalculateRateRequest(lessonList, createTeacher());
        LessonCalculateRateResponse response = service.calculateTeachersEarning(request);
        assertEquals(0, response.getRemainingMinutes());
    }

    private Teacher createTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("FirstName");
        teacher.setLastName("LastName");
        teacher.setHourRate(new BigDecimal("10"));
        return teacher;
    }

    private StudentGroup createGroup() {
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setId(1L);
        studentGroup.setName("15/54A");
        studentGroup.setEducationYearStart(2021L);
        studentGroup.setEducationYearEnd(2022L);
        return studentGroup;
    }

    private Lesson createLesson(Long lessonId, String lessonName, Long lessonDuration) {
        Lesson lesson = new Lesson();
        lesson.setId(lessonId);
        lesson.setLessonName(lessonName);
        lesson.setTeacher(createTeacher());
        lesson.setStudentGroup(createGroup());
        lesson.setDurationMinutes(lessonDuration);
        lesson.setLessonDate(LocalDateTime.now());
        return lesson;
    }

    private void populateLessonList() {
        lessonList = new ArrayList<>();
        lessonList.add(createLesson(1L, "grammar", 60L));
        lessonList.add(createLesson(2L, "speaking", 60L));
        lessonList.add(createLesson(3L, "english", 60L));
    }

}