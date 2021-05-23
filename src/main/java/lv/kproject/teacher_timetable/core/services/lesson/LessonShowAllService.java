package lv.kproject.teacher_timetable.core.services.lesson;

import lv.kproject.teacher_timetable.core.database.LessonRepository;
import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonShowAllResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonShowAllService {

    private final LessonRepository repository;

    public LessonShowAllService(LessonRepository repository) {
        this.repository = repository;
    }

    public LessonShowAllResponse showAllLessons() {
        List<Lesson> lessonList = repository.findAll();
        return new LessonShowAllResponse(lessonList);
    }

}
