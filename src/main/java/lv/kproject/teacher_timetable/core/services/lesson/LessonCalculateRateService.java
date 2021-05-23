package lv.kproject.teacher_timetable.core.services.lesson;

import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonCalculateRateRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonCalculateRateResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class LessonCalculateRateService {

    public LessonCalculateRateService() {
    }

    public LessonCalculateRateResponse calculateTeachersEarning(LessonCalculateRateRequest request) {

        List<Lesson> lessonList = request.getLessonList();
        long totalNumberOfMinutesWorked = lessonList.stream()
                .mapToLong(Lesson::getDurationMinutes)
                .sum();
        long hourEquivalent = totalNumberOfMinutesWorked / 60;
        long minuteRemainingForHourEquivalent = totalNumberOfMinutesWorked % 60;

        Teacher teacher = request.getTeacher();
        BigDecimal teacherHourRate = teacher.getHourRate();

        BigDecimal totalMoneyEarned = (teacherHourRate.multiply(BigDecimal.valueOf(hourEquivalent)))
                .add(
                        ((BigDecimal.valueOf(minuteRemainingForHourEquivalent)).divide(new BigDecimal("60"),
                                2, RoundingMode.HALF_UP))
                                .multiply(teacherHourRate)
                );

        return new LessonCalculateRateResponse(
                totalNumberOfMinutesWorked, hourEquivalent, minuteRemainingForHourEquivalent, totalMoneyEarned);

    }

}
