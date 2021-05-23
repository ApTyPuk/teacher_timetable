package lv.kproject.teacher_timetable.core.responses.lesson;

import lv.kproject.teacher_timetable.core.errors.CoreError;
import lv.kproject.teacher_timetable.core.errors.CoreResponse;

import java.math.BigDecimal;
import java.util.List;

public class LessonCalculateRateResponse extends CoreResponse {

    private Long totalNumberOfMinutesWorked;
    private Long hourEquivalent;
    private Long remainingMinutes;
    private BigDecimal moneyEarned;

    public LessonCalculateRateResponse(List<CoreError> errors) {
        super(errors);
    }

    public LessonCalculateRateResponse(Long totalNumberOfMinutesWorked, Long hours, Long remainingMinutes, BigDecimal moneyEarned) {
        this.totalNumberOfMinutesWorked = totalNumberOfMinutesWorked;
        this.hourEquivalent = hours;
        this.remainingMinutes = remainingMinutes;
        this.moneyEarned = moneyEarned;
    }

    public Long getTotalNumberOfMinutesWorked() {
        return totalNumberOfMinutesWorked;
    }

    public Long getHourEquivalent() {
        return hourEquivalent;
    }

    public Long getRemainingMinutes() {
        return remainingMinutes;
    }

    public BigDecimal getMoneyEarned() {
        return moneyEarned;
    }
}
