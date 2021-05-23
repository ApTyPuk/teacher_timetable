package lv.kproject.teacher_timetable.core.services.matchers;

import lv.kproject.teacher_timetable.core.domain.Teacher;
import org.mockito.ArgumentMatcher;

import java.math.BigDecimal;

public class TeacherMatcher implements ArgumentMatcher<Teacher> {
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private BigDecimal hourRate;

    public TeacherMatcher(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public TeacherMatcher(String name, String lastName, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean matches(Teacher teacher) {
        return teacher.getName().equals(name)
                && teacher.getLastName().equals(lastName);
    }

}
