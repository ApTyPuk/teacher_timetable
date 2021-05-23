package lv.kproject.teacher_timetable.core.services.matchers;

import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import org.mockito.ArgumentMatcher;

import java.time.LocalDate;

public class GroupMatcher implements ArgumentMatcher<StudentGroup> {

    private Long id;
    private String name;
    private LocalDate educationYearStart;

    public GroupMatcher(String name, LocalDate educationYearStart) {
        this.name = name;
        this.educationYearStart = educationYearStart;
    }

    @Override
    public boolean matches(StudentGroup studentGroup) {

        return studentGroup.getName().equals(name)
                && studentGroup.getEducationYearStart().equals(educationYearStart);
    }

}
