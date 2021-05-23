package lv.kproject.teacher_timetable.core.requests.lesson;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class LessonIntervalRequest {

    @NotEmpty
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lessonStart;

    @NotEmpty
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lessonEnd;

    public LessonIntervalRequest() {
    }

    public LessonIntervalRequest(LocalDateTime lessonStart, LocalDateTime lessonEnd) {
        this.lessonStart = lessonStart;
        this.lessonEnd = lessonEnd;
    }

    public LocalDateTime getLessonStart() {
        return lessonStart;
    }

    public void setLessonStart(LocalDateTime lessonStart) {
        this.lessonStart = lessonStart;
    }

    public LocalDateTime getLessonEnd() {
        return lessonEnd;
    }

    public void setLessonEnd(LocalDateTime lessonEnd) {
        this.lessonEnd = lessonEnd;
    }
}
